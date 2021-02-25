package flub78.org.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mResult;
    private EditText mSizeInput;
    private EditText mWeightInput;

    private Button mComputeButton;
    private Button mClearButton;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.println(Log.INFO, "MainActivity", "main activity creation");
        Log.v("MainActivity", "verbose level is blue");
        Log.d("MainActivity", "debug level is green");
        Log.i("MainActivity", "info level is white");
        Log.w("MainActivity", "Warning level is yellow");
        Log.e("MainActivity", "Error level is red");
        Log.wtf("MainActivity", "What the f...? level is red");


        mResult = findViewById(R.id.activity_main_result_text);
        mSizeInput = findViewById(R.id.activity_main_size_input);
        mWeightInput = findViewById(R.id.activity_main_weight_input);

        mComputeButton = findViewById(R.id.activity_main_compute_button);
        mClearButton = findViewById(R.id.activity_main_clear_button);

        mComputeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked

                float size;
                float weight;

                // by default System.out.println is at the INFO level
                Log.d(TAG,"Computing IMC");
                // VERBOSE, DEBUG, INFO, XAR?ERROR, ASSERT
                Log.i(TAG, "Computing IMC");

                try {
                    weight = Float.parseFloat(mWeightInput.getText().toString());
                } catch (Exception e) {
                    warningPopup("Poids incorrect");
                    return;
                }

                try {
                    size = Float.parseFloat(mSizeInput.getText().toString());
                } catch (Exception e) {
                    warningPopup("Taille incorrecte");
                    return;
                }

                if ((size < 1.0) || (size > 2.2)) {
                    warningPopup("IMC non significatif pour cette taille");
                    return;
                }

                if ((weight < 40.0) || (weight > 200.2)) {
                    warningPopup("IMC non significatif pour ce poids");
                    return;
                }

                float imc = weight / (size * size);

                mResult.setText(getString(R.string.IMC, imc));

            }
        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                Log.d(TAG, "Clear data");
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
}