package flub78.org.imc.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by flub78 on 2021-03-03.
 *
 * Access to sqlite
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "weights";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user TEXT, " +
                    "weight REAL NOT NULL, " +
                    "size REAL NOT NULL, " +
                    "date TEXT, " +
                    "comment TEXT" +
                    ");";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade (" + oldVersion + ", " + newVersion + ")" );
        db.execSQL(TABLE_DROP);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onDowngrade (" + oldVersion + ", " + newVersion + ")" );
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
