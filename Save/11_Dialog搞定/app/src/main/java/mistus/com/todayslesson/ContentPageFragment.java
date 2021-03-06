package mistus.com.todayslesson;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mistus on 2016/8/5.
 */
public class ContentPageFragment extends Fragment {

    private Context Context;
    private View view;
    private TextView Title;
    private TextView PercentageForeword;
    private ImageView PercentageView;
    private int Percentage;
    private String ServiceClassName;
    private Spinner spinner;
    public String[] mApps = {
            "10%", "20%", "30%", "40%", "50%",
            "60%", "70%", "80%", "90%"
//            , "100%"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Bundle bundle = getArguments();
        ServiceClassName = new ReminderIconService().getClass().getName();

        view = inflater.inflate(R.layout.edit_content_page_fragment, container, false);
        Title = (TextView)view.findViewById(R.id.LessionTheme);
        Title.setText(bundle.getString("Theme")+"  ！！");

        PercentageForeword = (TextView)view.findViewById(R.id.PercentageForeword);
        PercentageForeword.setText("目前完成度為" + bundle.getInt("Percentage")+"%");

        PercentageView = (ImageView)view.findViewById(R.id.Percentage);
        Percentage = bundle.getInt("Percentage");
        setPercentageImage(Percentage);


        //back等刪
        Button button = (Button)view.findViewById(R.id.back);
        button.setOnClickListener(backButtonClickListener);

        //Servuce開關測試區
        Switch ServiceSwitch = (Switch)view.findViewById(R.id.ServiceSwitch);

        if(checkServiceStarded(ServiceClassName)){
            ServiceSwitch.setChecked(true);}
        ServiceSwitch.setOnClickListener(ServiceSwitchClickListener);

        //送出按鈕
        Button B1 = (Button)view.findViewById(R.id.Record);
        B1.setOnClickListener(recordButtonClickListener);

        ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spinner_item, mApps);
        spinner = (Spinner)view.findViewById(R.id.BlowDown);
        spinner.setAdapter(adapter);

        return view;
    }

    //紀錄的按鈕click事件
    private  Button.OnClickListener recordButtonClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Context = ((MainActivity)getActivity()).getContext();
            AlertDialog.Builder builder = new AlertDialog.Builder(Context);
            String Percentage = toString().valueOf(spinner.getSelectedItem());
            Percentage = Percentage.substring(0 ,Percentage.length()-1);

            String DialogMassage="追加進度"+Percentage+"%，確定嗎?";
            builder.setMessage(DialogMassage)
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // 左方按鈕方法
                            RecordTime();
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

    //依百分比顯示圖片
    public void setPercentageImage(int PercentageInteger){

        if(PercentageInteger>100){
            PercentageInteger = 100;}

        switch (PercentageInteger){
            case 0  : PercentageView.setImageResource(R.drawable.percentage_0);  break;
            case 10 : PercentageView.setImageResource(R.drawable.percentage_10); break;
            case 20 : PercentageView.setImageResource(R.drawable.percentage_20); break;
            case 30 : PercentageView.setImageResource(R.drawable.percentage_30); break;
            case 40 : PercentageView.setImageResource(R.drawable.percentage_40); break;
            case 50 : PercentageView.setImageResource(R.drawable.percentage_50); break;
            case 60 : PercentageView.setImageResource(R.drawable.percentage_60); break;
            case 70 : PercentageView.setImageResource(R.drawable.percentage_70); break;
            case 80 : PercentageView.setImageResource(R.drawable.percentage_80); break;
            case 90 : PercentageView.setImageResource(R.drawable.percentage_90); break;
            case 100 :PercentageView.setImageResource(R.drawable.percentage_100); break;
        }
    }


    //測試區 ON / OFF開關
    private  Button.OnClickListener ServiceSwitchClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getActivity().getApplicationContext(), ReminderIconService.class);

//            new ReminderIconService().getClass().getName();       GetClass活的寫法
            if(checkServiceStarded(ServiceClassName)){
                Log.e("System：", "StartClicked");

                getActivity().stopService(intent);
                ((MainActivity)getActivity()).serviceDisconnect();

            }else{
                Log.e("System：", "StartClicked");
                intent.putExtra("Percentage", Percentage);
                getActivity().startService(intent);
            }
        }
    };

    //回傳是否存在
    public boolean checkServiceStarded(String serviceClassName){

        ActivityManager activityManager = (ActivityManager) getActivity().getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);

        for (ActivityManager.RunningServiceInfo runningServiceInfo : services) {
            Log.e("services：", runningServiceInfo.service.getClassName());
            if (runningServiceInfo.service.getClassName().equals(serviceClassName)){
                return true;
            }
        }
        return false;
    };

    //紀錄時間
    private void RecordTime(){

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String Date = dateFormat.format(date);

        //這邊之後要修正不是更新物件 是 原本數值+物件
        String Percentage = toString().valueOf(spinner.getSelectedItem());
        Percentage = Percentage.substring(0 ,Percentage.length()-1);

        String SqlCommand = "update LessionTodayData SET Percentage = "+Percentage+" WHERE Id = "+Date;

//            SqlCommand = "update LessionTodayData SET Percentage = 80 WHERE Id = 20160816";
//            SqlCommand = "UPDATA LessionTodayData SET Percentage = "+S1+" WHERE Id = "+Date;

        SQLiteService SQLiteService = new SQLiteService();
        SQLiteService.runSQL(SqlCommand ,null ,getActivity().getApplicationContext());

        ((MainActivity)getActivity()).setDefaultFragment();

//            EnterPageFragment EnterPageFragment = new EnterPageFragment();
//            FragmentTransaction FragmentTransaction = getFragmentManager().beginTransaction();
//            FragmentTransaction.replace(R.id.displayPage, EnterPageFragment);
//            FragmentTransaction.commit();

    }

    //回上一頁
    private  Button.OnClickListener backButtonClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            EnterPageFragment EnterPageFragment = new EnterPageFragment();
            FragmentTransaction FragmentTransaction = getFragmentManager().beginTransaction();
            FragmentTransaction.replace(R.id.displayPage, EnterPageFragment);
            FragmentTransaction.commit();
        }
    };
}
