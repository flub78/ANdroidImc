package flub78.org.imc.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import flub78.org.imc.model.DAOBase;
import flub78.org.imc.model.WeightRecord;

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
        Log.i(TAG, "count ... " );

        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = mDb.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    /**
     * @param w the record to add to the base
     */
    public void create(float weight, float size, String user, String date, String comment) {

        Log.d(TAG, "create (weight=" + weight + ", size=" + size +
                ", user=" + user + ", date=" + date + ", comment=" + comment);

        ContentValues value = new ContentValues();
        value.put(WEIGHT, weight);
        value.put(SIZE, size);
        value.put(USER, user);
        value.put(DATE, date);
        value.put(COMMENT, comment);

        mDb.insert(TABLE_NAME, null, value);
    }

    /**
     * @param w the record to add to the base
     */
    public void create(WeightRecord w) {

        Log.d(TAG, "create");

        this.create(w.getWeight(), w.getSize(), w.getUser(), w.getDate(), w.getComment());
    }

    /**
     * @param id to delete
     */
    public void delete(long id) {
        Log.d(TAG, "delete " + id);
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

        List<WeightRecord> weightList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        Cursor cursor = mDb.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding to the list
                 WeightRecord w = new WeightRecord(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getFloat(2),
                        cursor.getFloat(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );

                weightList.add(w);
            } while (cursor.moveToNext());
        }

        cursor.close();

        // return the list
        return weightList;
    }

    /**
     * @param id l'identifiant du métier à récupérer
     */
    public WeightRecord select(long id) {

        Log.d(TAG, "select " + id);

        Cursor cursor = mDb.query(TABLE_NAME, new String[] { KEY_ID,
                        USER, WEIGHT, SIZE, DATE, COMMENT}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        WeightRecord w = new WeightRecord(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getFloat(2),
                cursor.getFloat(3),
                cursor.getString(4),
                cursor.getString(5)
        );
        cursor.close();
        return w;
    }
}

