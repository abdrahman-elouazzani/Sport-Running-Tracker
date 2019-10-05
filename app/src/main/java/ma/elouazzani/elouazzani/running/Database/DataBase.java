package ma.elouazzani.elouazzani.running.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elouazzani on 09/12/2016.
 */
public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="run.db";
    private static final String TABLE_NAME="run";
    private static final String KEY_ID="id";
    private static final String KEY_DATE="date";
    private static final String KEY_DISTANCE="distance";
    private static final String KEY_TIME="time";

    private static String Craete_Table="CREATE TABLE " +TABLE_NAME+
    "("+KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
    +KEY_DATE+ " TEXT,"+KEY_DISTANCE+" REAL,"+KEY_TIME+" TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DataBase(Context context) {
        super(context,TABLE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL(Craete_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    // insert data into dataBase
    public void putData(Data data)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_DATE,data.getDate());
        contentValues.put(KEY_DISTANCE,data.getDistance());
        contentValues.put(KEY_TIME,data.getTime());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    // get data from dataBase
    public List<Data> getAllData()
    {
        List<Data> datas=new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
            do{
                Data data=new Data(cursor.getString(1),Double.parseDouble(cursor.getString(2)),cursor.getString(3));
                data.setId(Integer.parseInt(cursor.getString(0)));
                datas.add(data);
            }while (cursor.moveToNext());

        return datas;
    }
    // delete a single data from dataBase
    public void deleData(Data data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query="DELETE FROM "+TABLE_NAME+ " WHERE "+KEY_ID+" ='"+data.getId()+"'";
        db.execSQL(query);
        db.close();
    }

}
