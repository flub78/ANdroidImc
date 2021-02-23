package flub78.org.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mResult;
    private EditText mSizeInput;
    private EditText mWeightInput;

    private Button mComputeButton;
    private Button mClearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResult = (TextView) findViewById(R.id.activity_main_result_text);
        mSizeInput = (EditText) findViewById(R.id.activity_main_size_input);
        mWeightInput = (EditText) findViewById(R.id.activity_main_weight_input);

        mComputeButton = (Button) findViewById(R.id.activity_main_compute_button);
        mClearButton = (Button) findViewById(R.id.activity_main_clear_button);

        mComputeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                System.out.println("Compute IMC");

                mResult.setText("IMC = 27.7");

            }
        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                System.out.println("Clear data");
                mSizeInput.setText("");
                mWeightInput.setText("");
                mResult.setText("");
            }
        });

    }
}