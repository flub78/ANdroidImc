package flub78.org.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

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

                float size;
                float weight;

                System.out.println("Compute IMC");

                try {
                    weight = Float.parseFloat(mWeightInput.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Poid incorrect", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    size = Float.parseFloat(mSizeInput.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Taille incorrecte", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ((size < 1.0) || (size > 2.2)) {
                    Toast.makeText(getApplicationContext(), "IMC non significatif pour cette taille", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ((weight < 40.0) || (weight > 200.2)) {
                    Toast.makeText(getApplicationContext(), "IMC non significatif pour ce poid", Toast.LENGTH_SHORT).show();
                    return;
                }

                float imc = weight / (size * size);

                mResult.setText("IMC = " + new DecimalFormat("#.##").format(imc));

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