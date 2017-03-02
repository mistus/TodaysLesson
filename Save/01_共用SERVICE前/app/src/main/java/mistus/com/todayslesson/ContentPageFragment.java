package mistus.com.todayslesson;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mistus on 2016/8/5.
 */
public class ContentPageFragment extends Fragment {


    public String[] mApps = {
            "10%",
            "20%",
            "30%",
            "40%",
            "50%",
            "60%",
            "70%",
            "80%",
            "90%",
            "100%"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Bundle bundle = getArguments();
        View view = inflater.inflate(R.layout.content_page_fragment, container, false);
        TextView Title = (TextView)view.findViewById(R.id.LessionTheme);
        Title.setText(bundle.getString("theme")+"  ！！");

        //test
//        Button B1 = (Button)view.findViewById(R.id.Record);
//        B1.setOnClickListener(buttonClickListener);

        ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, mApps);
        Spinner spinner = (Spinner)view.findViewById(R.id.BlowDown);
        spinner.setAdapter(adapter);

//        Title.setText("123");
        return view;
//        return inflater.inflate(R.layout.content_page_fragment, container, false);
    }

//    private  Button.OnClickListener buttonClickListener = new Button.OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//
//
//            runSqlRawQuery("INSERT INTO LessionTodayData VALUES(20160810 ,'20160810' ,'看書' ,20)",null);
//        }
//    };
//
//    private DatabaseOpenHelper DatabaseOpenHelper;
//    private String runSqlRawQuery(String SQL, String[] selectionArgs) {
//
//        Cursor cursor = null;
//        SQLiteDatabase DB;
//        try {
//            DatabaseOpenHelper = new DatabaseOpenHelper(getActivity().getApplicationContext());
//            DB = DatabaseOpenHelper.getWritableDatabase();
//            String Date = null;
////            cursor = DB.query("LessionToday.db", null, "Date=","","","","","","","","","","","","",);
//
//            Log.e("TESTEST",SQL);
//            cursor = DB.rawQuery(SQL, selectionArgs);
//            if(cursor == null || cursor.getCount() == 0){
//                return "Null Desu";
//            };
//
//            cursor.moveToFirst();
//            Log.e("System", cursor.getString(cursor.getColumnIndex("Date")));
//            Date = cursor.getString(cursor.getColumnIndex("Date"));
//            DB.close();
//            return Date;
//
//        }catch (Exception e){
//            DatabaseOpenHelper.close();
//            Log.e("System", "SqlRawQuery Error");
//            return "System Search Error";
//
//        }finally {
//
//            if(cursor != null){
//                cursor.close();}
//        }
//    }

}
