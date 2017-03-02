package mistus.com.todayslesson;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by mistus on 2016/8/11.
 */
public class SQLiteService {
    private String runSqlRawQuery(String SQL, String[] selectionArgs, ) {

        Cursor cursor = null;
        SQLiteDatabase DB;
        try {
            new DatabaseOpenHelper = new DatabaseOpenHelper(this);
            DB = DatabaseOpenHelper.getWritableDatabase();
            String Date = null;
//            cursor = DB.query("LessionToday.db", null, "Date=","","","","","","","","","","","","",);

            Log.e("SQL:",SQL);
            cursor = DB.rawQuery(SQL, selectionArgs);
            if(cursor == null || cursor.getCount() == 0){
                return "Null Desu";
            };

            cursor.moveToFirst();
            Log.e("SystemGetDate:", cursor.getString(cursor.getColumnIndex("Date")));
            Date = cursor.getString(cursor.getColumnIndex("Date"));
            DB.close();
            return Date;

        }catch (Exception e){
            Log.e("System", "SqlRawQuery Error");
            return "System Search Error";

        }finally {
            DatabaseOpenHelper.close();
            if(cursor != null){
                cursor.close();}
        }
    }

}
