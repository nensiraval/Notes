package com.example.notes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Mydatabase extends SQLiteOpenHelper {

    public Mydatabase(Context context) {
        super(context,"Database.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Table = "CREATE TABLE note (id integer PRIMARY KEY AUTOINCREMENT,tittle TEXT,distext TEXT,userid integer)";
        db.execSQL(Table);
    }

    public Boolean insertdata( String tittle, String distext,int userid)
    {
        try
        {
            String insert ="INSERT INTO note(tittle, distext,userid) VALUES ('"+tittle+"','"+distext+"',"+userid+")";
            getWritableDatabase().execSQL(insert);
            return true;
        }catch (Exception e)
        {
            Log.e("________", "insertdata: "+e );
            return false;
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor data(int userid) {

        String cursor = "SELECT * FROM note WHERE userid = " + userid;

        return getReadableDatabase().rawQuery(cursor, null);
    }
    public void editdata(String newtittle, String description, int editid)
    {
        String update = "UPDATE note SET tittle = '" + newtittle + "', distext = '" + description + "' WHERE id = " + editid;
        getWritableDatabase().execSQL(update);

    }
    public void deletedata(int editid) {
        String delete = "DELETE FROM note  WHERE id = " + editid;
        getWritableDatabase().execSQL(delete);
    }
}
