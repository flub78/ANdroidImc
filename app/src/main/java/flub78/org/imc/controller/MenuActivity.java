package flub78.org.imc.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import flub78.org.imc.R;

public class MenuActivity extends AppCompatActivity  {

    private static final String TAG = "MenuActivity";

    private Menu m = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.imc_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        //R.menu.menu est l'id de notre menu
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void menu_item_2_click(MenuItem item) {
    }

    public void menu_item_1_click(MenuItem item) {
    }
}
