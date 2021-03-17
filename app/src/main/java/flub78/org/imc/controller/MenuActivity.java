package flub78.org.imc.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import flub78.org.imc.ListActivity;
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

    public void menu_list_click(MenuItem item) {
        Log.d(TAG, "menu_list_click");

        Intent i = new Intent(this, ListActivity.class);

        startActivity(i);
    }

    public void menu_logout_click(MenuItem item) {
        Log.d(TAG, "menu_logout_click");

        // probably OK as long as there are only two activities
        // it should likely be refactored it the menu navigates through several activities
        finish();
    }
}
