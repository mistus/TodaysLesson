package mistus.com.todayslesson;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mistus on 2016/8/4.
 */
public class EnterPageFragment extends Fragment {

    private TextView LessonEnter;
    private  Button LessonEnterButton;
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

            SQLiteService SQLiteService = new SQLiteService();

            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String Date = dateFormat.format(date);

            String SqlCommand = "INSERT INTO LessionTodayData VALUES("+Date+" ,'"+Date+"' ,'"+LessonEnter.getText()+"' ,0)";
            SQLiteService.runSQL(SqlCommand ,null ,getActivity().getApplicationContext());
        }
    };

}
