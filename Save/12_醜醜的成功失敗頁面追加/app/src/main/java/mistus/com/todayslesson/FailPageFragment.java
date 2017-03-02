package mistus.com.todayslesson;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by mistus on 2016/8/25.
 */
public class FailPageFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fail_page_fragment, container, false);
//        LessonEnterButton = (Button)view.findViewById(R.id.LessonEnterButton);
//        LessonEnterButton.setOnClickListener(buttonClickListener);
//        LessonEnter = (TextView)view.findViewById(R.id.LessonEnter);
//back等刪
        Button BackbBtton = (Button)view.findViewById(R.id.back);
        BackbBtton.setOnClickListener(backButtonClickListener);

        return view;
    }

    //回上一頁
    private  Button.OnClickListener backButtonClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {


            ((MainActivity)getActivity()).setDefaultFragment();
//            ContentPageFragment ContentPageFragment = new ContentPageFragment();
//            FragmentTransaction FragmentTransaction = getFragmentManager().beginTransaction();
//            FragmentTransaction.replace(R.id.displayPage, ContentPageFragment);
//            FragmentTransaction.commit();
        }
    };
}
