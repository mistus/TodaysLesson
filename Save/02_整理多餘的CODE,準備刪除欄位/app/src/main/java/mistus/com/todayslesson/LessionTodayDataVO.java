package mistus.com.todayslesson;

import android.database.Cursor;
import android.util.Log;

/**
 * Created by mistus on 2016/8/11.
 */
public class LessionTodayDataVO {

    private int Id;
    private String Date;
    private String Theme;
    private int Percentage;
    public LessionTodayDataVO(){}
    public LessionTodayDataVO(Cursor cursor) {
        cursor.moveToFirst();
        setId(cursor.getInt(cursor.getColumnIndex("Id")));
        setDate(cursor.getString(cursor.getColumnIndex("Date")));
        setTheme(cursor.getString(cursor.getColumnIndex("Theme")));
        setPercentage(cursor.getInt(cursor.getColumnIndex("Percentage")));
        Log.e("TEST",String.valueOf(Id)+Date + Theme+String.valueOf(Percentage));
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setTheme(String theme) {
        Theme = theme;
    }

    public void setPercentage(int percentage) {
        Percentage = percentage;
    }

    public int getId() {
        return Id;
    }

    public String getDate() {
        return Date;
    }

    public String getTheme() {
        return Theme;
    }

    public int getPercentage() {
        return Percentage;
    }
}
