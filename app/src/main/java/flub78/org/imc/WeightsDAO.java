package flub78.org.imc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flub78 on 2021-03-03.
 */
public class WeightsDAO extends DAOBase {

    private static final String TAG = "WeightsDAO";

    public static final String TABLE_NAME = "weights";

    public static final String KEY_ID = "id";
    public static final String USER = "user";
    public static final String WEIGHT = "weight";
    public static final String SIZE = "size";
    public static final String DATE = "date";
    public static final String COMMENT = "comment";

    public WeightsDAO(Context pContext) {
        super(pContext);
    }

    /**
     * @return the number of element in the table
     */
    public int count() {
        return 0;
    }

    /**
     * @param w the record to add to the base
     */
    public void create(WeightRecord w) {

        Log.d(TAG, "create");

        ContentValues value = new ContentValues();
        value.put(WEIGHT, w.getWeight());
        value.put(SIZE, w.getSize());
        value.put(USER, w.getUser());
        value.put(DATE, w.getDate());
        value.put(COMMENT, w.getComment());

        mDb.insert(TABLE_NAME, null, value);
    }

    /**
     * @param id to delete
     */
    public void delete(long id) {
        Log.d(TAG, "delete");
        mDb.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    /**
     * @param w the record to change
     */
    public void update(WeightRecord w) {
        Log.d(TAG, "update");

        ContentValues value = new ContentValues();
        value.put(WEIGHT, w.getWeight());
        value.put(SIZE, w.getSize());
        value.put(USER, w.getUser());
        value.put(DATE, w.getDate());
        value.put(COMMENT, w.getComment());
        mDb.update(TABLE_NAME, value, KEY_ID +
                " = ?", new String[]{String.valueOf(w.getId())});
        // CODE
    }

    public List<WeightRecord> getAll() {
        Log.i(TAG, "getAll ... ");

        List<WeightRecord> weightList = new ArrayList<WeightRecord>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        Cursor cursor = mDb.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding to the list
                WeightRecord note = new WeightRecord(
                        Integer.parseInt(cursor.getString(0)),
                        Float.parseFloat(cursor.getString(1)),
                        Float.parseFloat(cursor.getString(2)),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );

                weightList.add(note);
            } while (cursor.moveToNext());
        }

        // return the list
        return weightList;
    }

    /**
     * @param id l'identifiant du métier à récupérer
     */
    public WeightRecord select(long id) {

        Log.d(TAG, "select");
        /**
         Cursor c = mDb.rawQuery("select " +
         INTITULE + " from " + TABLE_NAME + " where salaire > ?", new String[]{"1"});

         // CODE
         while (c.moveToNext()) {
         // Faire quelque chose
         }
         c.close();
         */
        return null;
    }
}

