package mistus.com.todayslesson;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by mistus on 2016/8/5.
 */
public class ContentPageFragment extends Fragment {

    private View view;
    private TextView Title;
    private TextView PercentageForeword;
    private ImageView Percentage;

    public String[] mApps = {
            "10%", "20%", "30%", "40%", "50%",
            "60%", "70%", "80%", "90%", "100%"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Bundle bundle = getArguments();
//        view = inflater.inflate(R.layout.content_page_fragment, container, false);
        view = inflater.inflate(R.layout.edit_content_page_fragment, container, false);
        Title = (TextView)view.findViewById(R.id.LessionTheme);
        Title.setText(bundle.getString("Theme")+"  ！！");


        PercentageForeword = (TextView)view.findViewById(R.id.PercentageForeword);
        PercentageForeword.setText("目前完成度為" + bundle.getInt("Percentage")+"%");

        Percentage = (ImageView)view.findViewById(R.id.Percentage);
        setPercentageImage(bundle.getInt("Percentage"));

        Switch sw1 = (Switch)view.findViewById(R.id.sw1);
//        sw1.seto
        //送出按鈕
        Button B1 = (Button)view.findViewById(R.id.Record);
        B1.setOnClickListener(buttonClickListener);

        ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spinner_item, mApps);
        Spinner spinner = (Spinner)view.findViewById(R.id.BlowDown);
        spinner.setAdapter(adapter);

        return view;
    }

    private  Button.OnClickListener buttonClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            EnterPageFragment EnterPageFragment = new EnterPageFragment();
            FragmentTransaction FragmentTransaction = getFragmentManager().beginTransaction();
            FragmentTransaction.replace(R.id.displayPage, EnterPageFragment);
            FragmentTransaction.commit();
        }
    };

    public void setPercentageImage(int PercentageInteger){

        switch (PercentageInteger){
            case 0  : Percentage.setImageResource(R.drawable.percentage_0);  break;
            case 10 : Percentage.setImageResource(R.drawable.percentage_10); break;
            case 20 : Percentage.setImageResource(R.drawable.percentage_20); break;
            case 30 : Percentage.setImageResource(R.drawable.percentage_30); break;
            case 40 : Percentage.setImageResource(R.drawable.percentage_40); break;
            case 50 : Percentage.setImageResource(R.drawable.percentage_50); break;
            case 60 : Percentage.setImageResource(R.drawable.percentage_60); break;
            case 70 : Percentage.setImageResource(R.drawable.percentage_70); break;
            case 80 : Percentage.setImageResource(R.drawable.percentage_80); break;
            case 90 : Percentage.setImageResource(R.drawable.percentage_90); break;
            case 100 :Percentage.setImageResource(R.drawable.percentage_100); break;
        }


    }

}
