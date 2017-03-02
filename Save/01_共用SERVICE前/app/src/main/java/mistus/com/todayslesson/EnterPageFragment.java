package mistus.com.todayslesson;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
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

    private TextView Title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.enter_page_fragment, container, false);
        Title = (TextView) view.findViewById(R.id.TitleText);

//        Title.setText("123");
        return view;
//        return inflater.inflate(R.layout.enter_page_fragment, container, false);
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
////        Title = (TextView) getView().findViewById(R.id.TitleText);
//        super.onActivityCreated(savedInstanceState);
//    }
private  Button.OnClickListener buttonClickListener = new Button.OnClickListener() {

    @Override
    public void onClick(View v) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String Date = dateFormat.format(date);

//        runSqlRawQuery("INSERT INTO LessionTodayData VALUES("+Date+" ,'"+Date+"' ,'看書' ,0)",null);
    }
};

}
