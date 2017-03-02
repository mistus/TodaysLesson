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

    private EnterPageFragment EnterPageFragment;
    private ContentPageFragment  ContentPageFragment;
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
        //取得系統時間yyyyMMdd
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String Date = dateFormat.format(date);

//        String Date = runSqlRawQuery("select * from LessionTodayData where Id = ?",new String[] {String.valueOf(20160810)});
        String SQL = "select * from LessionTodayData where Id = ?";
//        String SQL = "delete from LessionTodayData where Id = ?";

        LessionTodayDataVO LessionTodayDataVO = new SQLiteService().runSQL(SQL, new String[] {Date}, this);
        if(LessionTodayDataVO==null){

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
            bundle.putInt("Id",LessionTodayDataVO.getId());
            bundle.putString("Date",LessionTodayDataVO.getDate());
            bundle.putString("Theme",LessionTodayDataVO.getTheme());
            bundle.putInt("Percentage",LessionTodayDataVO.getPercentage());
            ContentPageFragment.setArguments(bundle);
            FragmentTransaction.commit();

        }

    }

}
