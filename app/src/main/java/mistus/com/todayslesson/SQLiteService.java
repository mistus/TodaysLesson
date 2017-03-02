package mistus.com.todayslesson;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by mistus on 2016/8/11.
 */
public class SQLiteService {

    public LessionTodayDataVO runSQL(String SQL, String[] selectionArgs, Context context) {

        Cursor cursor = null;
        SQLiteDatabase DB;
        try {
            DatabaseOpenHelper DatabaseOpenHelper = new DatabaseOpenHelper(context);
            DB = DatabaseOpenHelper.getWritableDatabase();

            //完成後刪除LOG
            Log.e("SQL:",SQL);
            cursor = DB.rawQuery(SQL, selectionArgs);
            if(cursor == null || cursor.getCount() == 0){
                return null;
            };

            cursor.moveToFirst();
            return new LessionTodayDataVO(cursor);

        }catch (Exception e){
            Log.e("System", "SqlRawQuery Error");
            return null;
        }finally {
            if(cursor != null){
                cursor.close();}
        }
    }

}
