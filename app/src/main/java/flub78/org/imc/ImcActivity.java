package flub78.org.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ImcActivity extends AppCompatActivity implements View.OnKeyListener {

    private TextView mResult;
    private EditText mSizeInput;
    private EditText mWeightInput;

    private static final String TAG = "MainActivity";

    public void computeImc(View v)  {
        // The user just clicked

        float size;
        float weight;

        // by default System.out.println is at the INFO level
        // VERBOSE, DEBUG, INFO, XAR?ERROR, ASSERT
        Log.i(TAG, getString(R.string.computing));

        try {
            weight = Float.parseFloat(mWeightInput.getText().toString());
        } catch (Exception e) {
            warningPopup(getString(R.string.wrong_weight));
            return;
        }

        try {
            size = Float.parseFloat(mSizeInput.getText().toString());
        } catch (Exception e) {
            warningPopup(getString(R.string.wrong_size));
            return;
        }

        if ((size < 1.0) || (size > 2.2)) {
            warningPopup(getString(R.string.cannot_compute));
            return;
        }

        if ((weight < 40.0) || (weight > 200.2)) {
            warningPopup(getString(R.string.cannot_compute));
            return;
        }

        float imc = weight / (size * size);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.println(Log.INFO, "MainActivity", getString(R.string.create_main));
        Log.v("MainActivity", getString(R.string.verbose_log_example));
        Log.d("MainActivity", getString(R.string.debug_log_example));
        Log.i("MainActivity", getString(R.string.info_log_example));
        Log.w("MainActivity", getString(R.string.warning_log_example));
        Log.e("MainActivity", getString(R.string.error_log_example));
        Log.wtf("MainActivity", getString(R.string.wtf_log_example));


        mResult = findViewById(R.id.activity_main_result_text);
        mSizeInput = findViewById(R.id.activity_main_size_input);
        mWeightInput = findViewById(R.id.activity_main_weight_input);

        Button computeButton = findViewById(R.id.activity_main_compute_button);
        Button clearButton = findViewById(R.id.activity_main_clear_button);

        mSizeInput.setOnKeyListener(this);
        mWeightInput.setOnKeyListener(this);
        computeButton.setOnClickListener(this::computeImc);

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

        /*
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
        */
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            this.computeImc(v);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}