package flub78.org.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import flub78.org.imc.model.WeightRecord;
import flub78.org.imc.model.WeightsDAO;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";
    private ListView listView;
    private ArrayAdapter<WeightRecord> listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Log.d(TAG, "onCreate");

        // Get ListView object from xml
        this.listView = (ListView) findViewById(R.id.listView);

        WeightsDAO dao = new WeightsDAO(this);

        dao.open();

        /*
        TODO : load records into the listView
         */
        List<WeightRecord> l = dao.getAll();

        this.listViewAdapter = new ArrayAdapter<WeightRecord>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, l);
        // Assign adapter to ListView
        this.listView.setAdapter(this.listViewAdapter);
        dao.close();
    }
}