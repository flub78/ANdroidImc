package flub78.org.imc.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by flub78 on 2021_03-03.
 */
public abstract class DAOBase {
    // Nous sommes à la première version de la base
    // Si je décide de la mettre à jour, il faudra changer cet attribut
    protected final static int VERSION = 1;
    // Le nom du fichier qui représente ma base
    protected final static String NAME = "database.db";

    private final String TAG = "DAOBase";

    protected SQLiteDatabase mDb = null;
    protected DatabaseHandler mHandler;

    public DAOBase(Context pContext) {
        Log.i(TAG, "constructor");

        mHandler = new DatabaseHandler(pContext, NAME, null, VERSION);
    }

    public SQLiteDatabase open() {

        Log.i(TAG, "open");

        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        Log.i(TAG, "close");
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        Log.i(TAG, "getDB");
        return mDb;
    }
}

