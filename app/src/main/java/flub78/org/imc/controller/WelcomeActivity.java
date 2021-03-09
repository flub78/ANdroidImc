package flub78.org.imc.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import flub78.org.imc.R;
import flub78.org.imc.model.User;

import static flub78.org.imc.Shared.PREF_KEY_USER_NAME;

public class WelcomeActivity extends AppCompatActivity {

    private EditText mNameInput;
    private Button mButton;
    private User mUser = new User();

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        mPreferences = getPreferences(MODE_PRIVATE);

        mNameInput = (EditText) findViewById(R.id.activity_welcome_name_input);
        mButton = findViewById(R.id.login);

        String name = mPreferences.getString(PREF_KEY_USER_NAME, null);
        if (null == name) {
            mButton.setEnabled(false);
        } else {
            mNameInput.setText(name);
        }

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This is where we'll check the user input
                mButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUser.setFirstName(mNameInput.getText().toString());
                mPreferences.edit().putString(PREF_KEY_USER_NAME, mUser.getFirstName()).apply();
                Intent intent = new Intent(WelcomeActivity.this, ImcActivity.class);

                intent.putExtra(PREF_KEY_USER_NAME, mUser.getFirstName());

                startActivity(intent);
            }
        });

    }
}