package flub78.org.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }

    public String getUserToken() {
        Log.d("AuthActivity", "getUserToken");
        SharedPreferences prefs = getSharedPreferences("UserToken", MODE_PRIVATE);

        // Fetches the token, does a lot of things, then...
        return "IncredibleToken";
    }
}