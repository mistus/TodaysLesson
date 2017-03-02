package mistus.com.todayslesson;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mistus on 2016/8/5.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "LessionToday.db";
    public static final String TABLE_NAME = "LessionTodayData";

    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "create table if not exists " + TABLE_NAME + "" +
                " (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Date TEXT NOT NULL, " +
                    "Theme TEXT NOT NULL, " +
                    "Percentage INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
