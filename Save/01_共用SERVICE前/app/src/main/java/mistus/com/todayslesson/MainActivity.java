package mistus.com.todayslesson;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button startButton ;
    Button cancelButton ;
    Intent intent ;
    private EnterPageFragment EnterPageFragment;
    private ContentPageFragment  ContentPageFragment;
    private DatabaseOpenHelper DatabaseOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //設置fragment
        setDefaultFragment();


    }

    //設定初始Fragment
    private void setDefaultFragment()
    {
//        runSqlRawQuery("INSERT INTO LessionTodayData VALUES(20160810 ,'20160810' ,'看書' ,20)",null);

        //取得系統時間yyyyMMdd
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String Date = dateFormat.format(date);

//        String Date = runSqlRawQuery("select * from LessionTodayData where Id = ?",new String[] {String.valueOf(20160810)});
        Date = runSqlRawQuery("select * from LessionTodayData where Id = ?",new String[] {Date});
        if(Date.equals("Null Desu")){

            EnterPageFragment = new EnterPageFragment();
            FragmentTransaction FragmentTransaction = getFragmentManager().beginTransaction();
            FragmentTransaction.replace(R.id.displayPage, EnterPageFragment);
            FragmentTransaction.commit();
        }else{

        //測試contentPage fragment OK
        ContentPageFragment = new ContentPageFragment();
        FragmentTransaction FragmentTransaction = getFragmentManager().beginTransaction();
        FragmentTransaction.replace(R.id.displayPage, ContentPageFragment);

            //郵差放置 OK
            Bundle bundle = new Bundle();
            bundle.putString("theme", Date);
            ContentPageFragment.setArguments(bundle);
            FragmentTransaction.commit();

        }

        //輸入fragment OK
//        EnterPageFragment = new EnterPageFragment();
//        FragmentTransaction FragmentTransaction = getFragmentManager().beginTransaction();//開始一個事物
//        FragmentTransaction.replace(R.id.displayPage, EnterPageFragment);
//        FragmentTransaction.commit();
        //測試contentPage fragment OK
//        ContentPageFragment = new ContentPageFragment();
//        FragmentTransaction FragmentTransaction = getFragmentManager().beginTransaction();
//        FragmentTransaction.replace(R.id.displayPage, ContentPageFragment);
        //郵差放置 OK
//        Bundle bundle = new Bundle();
//        bundle.putString("theme", "LessionToday");
//        bundle.putString("theme", Date);
//        ContentPageFragment.setArguments(bundle);
//        FragmentTransaction.commit();



    }

    //取資料程式
    private void getDateAndPercentageFromDatabase() {

        Cursor cursor = null;
        SQLiteDatabase DB;
        try {
            DatabaseOpenHelper = new DatabaseOpenHelper(this);
            DB = DatabaseOpenHelper.getWritableDatabase();

//            cursor = DB.query("LessionToday.db", null, "Date=","","","","","","","","","","","","",);
            cursor = DB.rawQuery("", null);
            DB.close();

        }catch (Exception e){
            Log.e("System", "getDateAndPercentage Error");
        }finally {
            DatabaseOpenHelper.close();
            if(cursor != null){
            cursor.close();}
        }
    }

    //SQL執行便利程式

    private String runSqlRawQuery(String SQL, String[] selectionArgs) {

        Cursor cursor = null;
        SQLiteDatabase DB;
        try {
            DatabaseOpenHelper = new DatabaseOpenHelper(this);
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
