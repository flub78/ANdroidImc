package flub78.org.imc.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import flub78.org.imc.helper.KeyBoard;
import flub78.org.imc.R;
import flub78.org.imc.model.WeightsDAO;

import static flub78.org.imc.Shared.PREF_KEY_USER_NAME;

/**
 * Activitiy to compute IMC and save a new record
 *
 * TODO: replace deprecated SimpleDateFormat. https://www.baeldung.com/migrating-to-java-8-date-time-api
 */
public class ImcActivity extends MenuActivity implements View.OnKeyListener {

    private SharedPreferences mPreferences;

    private TextView mName;
    private TextView mResult;
    private EditText mSizeInput;
    private EditText mWeightInput;
    private EditText mDateInput;
    private EditText mCommentInput;
    private DatePickerDialog mPicker;

    private float mCurrentSize;
    private float mCurrentWeight;

    private static final String TAG = "MainActivity";

    /**
     * Extract the data from the IMC activity form and check that
     * it is possible to compute a BMI. Return false and display warning when not.
     * @return false if it is not possible to compute a BMI
     */
    private boolean wrongInput () {

        Log.d(TAG, "wrongInput");

        try {
            mCurrentWeight = Float.parseFloat(mWeightInput.getText().toString());
        } catch (Exception e) {
            warningPopup(getString(R.string.wrong_weight));
            return true;
        }

        try {
            mCurrentSize = Float.parseFloat(mSizeInput.getText().toString());
        } catch (Exception e) {
            warningPopup(getString(R.string.wrong_size));
            return true;
        }

        if ((mCurrentSize < 1.0) || (mCurrentSize > 2.2)) {
            warningPopup(getString(R.string.cannot_compute));
            return true;
        }

        if ((mCurrentWeight < 40.0) || (mCurrentWeight > 200.2)) {
            warningPopup(getString(R.string.cannot_compute));
            return true;
        }
        return false;
    }

    /**
     * Compute BMI from data in the form
     * @param v
     */
    public void computeImc(View v)  {
        // The user just clicked

        KeyBoard.hide(this);


        // by default System.out.println is at the INFO level
        // VERBOSE, DEBUG, INFO, XAR?ERROR, ASSERT
        Log.i(TAG, getString(R.string.computing));

        if (this.wrongInput()) return;

        float imc = mCurrentWeight / (mCurrentSize * mCurrentSize);

        mResult.setText(getString(R.string.IMC, imc));
        Resources res = getResources();

        if (imc < 16) {
            mResult.setTextColor(res.getColor(R.color.anorexy));
        } else if (imc < 18.5) {
            mResult.setTextColor(res.getColor(R.color.leanness));
        } else if (imc < 25) {
            mResult.setTextColor(res.getColor(R.color.normal));
        } else if (imc < 30) {
            mResult.setTextColor(res.getColor(R.color.overweigth));
        } else if (imc < 35) {
            mResult.setTextColor(res.getColor(R.color.moderate_obesity));
        } else if (imc < 40) {
            mResult.setTextColor(res.getColor(R.color.severe_obesity));
        } else {
            mResult.setTextColor(res.getColor(R.color.morbid_obesity));
        }
    }

    /**
     * Inherited from activity, initializes the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // the content view must be set before toolbar activation in the parent method
        setContentView(R.layout.activity_constraint_layout_imc);

        super.onCreate(savedInstanceState);


        Log.println(Log.INFO, "MainActivity", getString(R.string.create_main));
        Log.v("MainActivity", getString(R.string.verbose_log_example));
        Log.d("MainActivity", getString(R.string.debug_log_example));
        Log.i("MainActivity", getString(R.string.info_log_example));
        Log.w("MainActivity", getString(R.string.warning_log_example));
        Log.e("MainActivity", getString(R.string.error_log_example));
        Log.wtf("MainActivity", getString(R.string.wtf_log_example));

        mResult = findViewById(R.id.activity_imc_result_text);
        mSizeInput = findViewById(R.id.activity_imc_size_input);
        mWeightInput = findViewById(R.id.activity_imc_weight_input);
        mDateInput = findViewById(R.id.activity_imc_date_input);
        mName = findViewById(R.id.activity_imc_name);
        mCommentInput = findViewById(R.id.activity_imc_comment_input);

        // On récupère l'intent qui a lancé cette activité
        Intent i = getIntent();

        // get the user name and display it
        String name = i.getStringExtra(PREF_KEY_USER_NAME);
        if (null != name) {
            mName.setText(name);
        }

        Button computeButton = findViewById(R.id.activity_imc_compute_button);
        Button clearButton = findViewById(R.id.activity_imc_clear_button);

        mSizeInput.setOnKeyListener(this);
        mWeightInput.setOnKeyListener(this);
        computeButton.setOnClickListener(this::computeImc);


        String date_n = new SimpleDateFormat(getString(R.string.date_format), Locale.getDefault()).format(new Date());
        mDateInput.setText(date_n);

        mDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, getString(R.string.date_clicked));

                // get the current date
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // create a date picker dialog
                mPicker = new DatePickerDialog(ImcActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                Log.e(TAG, "dayOfMonth=" + dayOfMonth + ", monthOfYear=" + monthOfYear + ", year=" + year);
                                String date_n = new SimpleDateFormat(getString(R.string.date_format),
                                        Locale.getDefault()).format(new Date(year - 1900, monthOfYear, dayOfMonth));
                                mDateInput.setText(date_n);
                            }
                        }, year, month, day);
                mPicker.show();

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                Log.d(TAG, getString(R.string.clearing_data));
                mSizeInput.setText("");
                mWeightInput.setText("");
                mResult.setText("");
            }
        });
    }


    /**
     * Small warning for input errors
     * @param msg the string to display
     */
    void warningPopup(final String msg) {

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Compute BMI when Enter is pressed
     * @param v
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            this.computeImc(v);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    /**
     * on button store clicked
     * @param view
     */
    public void storeRecord(View view) {
        Log.d(TAG, "storeRecord");

        if (wrongInput()) return;

        WeightsDAO dao = new WeightsDAO(this);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        String strDate = mDateInput.getText().toString();
        String comment = mCommentInput.getText().toString();

        // The date picker already controls the date format
        Log.e(TAG, "date = " + strDate);

        // Generally it is a good rule of thumb to store dates in the persistent layer
        // in UTC to avoid subtle bugs. However for a codelab, as the use case saving with
        // a local and retreiving with another is unlikely, it is good enough.
         // TODO: refactor to save dates in UTC (mainly as an exercise)

    }

}