package mistus.com.todayslesson;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mistus on 2016/8/4.
 */
public class EnterPageFragment extends Fragment {

    private Context Context;
    private TextView LessonEnter;
    private  Button LessonEnterButton;
    private ContentPageFragment  ContentPageFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.enter_page_fragment, container, false);
        LessonEnterButton = (Button)view.findViewById(R.id.LessonEnterButton);
        LessonEnterButton.setOnClickListener(buttonClickListener);
        LessonEnter = (TextView)view.findViewById(R.id.LessonEnter);

        return view;
    }

    private  Button.OnClickListener buttonClickListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            Context = ((MainActivity)getActivity()).getContext();
            AlertDialog.Builder builder = new AlertDialog.Builder(Context);
            builder.setMessage("確定後無法變更，是否登記今日的課題？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // 左方按鈕方法
                            EnterContentPage();
                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // 右方按鈕方法
                           return;
                        }
                    });
            AlertDialog dialogMassage = builder.create();
            dialogMassage.show();
        }
    };


    public void EnterContentPage(){

        SQLiteService SQLiteService = new SQLiteService();

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String Date = dateFormat.format(date);

        String SqlCommand = "INSERT INTO LessionTodayData VALUES("+Date+", '"+LessonEnter.getText()+"' ,0)";
        SQLiteService.runSQL(SqlCommand ,null ,getActivity().getApplicationContext());

        SqlCommand = "select * from LessionTodayData where Id = ?";
        LessionTodayDataVO LessionTodayDataVO = new SQLiteService().runSQL(SqlCommand, new String[] {Date}, getActivity().getApplicationContext());
        //測試contentPage fragment OK
        ContentPageFragment = new ContentPageFragment();
        FragmentTransaction FragmentTransaction = getFragmentManager().beginTransaction();
        FragmentTransaction.setCustomAnimations(R.animator.page_no_change, R.animator.page_out);
        FragmentTransaction.replace(R.id.displayPage, ContentPageFragment);

        //郵差放置 OK
        Bundle bundle = new Bundle();
        bundle.putInt("Id",LessionTodayDataVO.getId());
        bundle.putString("Theme",LessionTodayDataVO.getTheme());
        bundle.putInt("Percentage",LessionTodayDataVO.getPercentage());
        ContentPageFragment.setArguments(bundle);
        FragmentTransaction.commit();

    }

}

