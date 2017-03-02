package mistus.com.todayslesson;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by mistus on 2016/8/5.
 */
public class ContentPageFragment extends Fragment {

    private View view;
    private TextView Title;
    private TextView Percentage;

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
        Percentage= (TextView)view.findViewById(R.id.Percentage);
        Percentage.setText(bundle.getInt("Percentage")+" ％");

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


}
