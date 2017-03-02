package mistus.com.todayslesson;


import android.app.ActivityManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ReminderIconService ReminderIconService;
    private ServiceConnection SeverConnection;
    private EnterPageFragment EnterPageFragment;
    private ContentPageFragment  ContentPageFragment;
    public int Percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //設置fragment
        setDefaultFragment();
    }

    //設定初始Fragment
    public void setDefaultFragment()
    {
        //取得系統時間yyyyMMdd
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String Date = dateFormat.format(date);

        String SQL = "select * from LessionTodayData where Id = ?";
//        String SQL = "delete from LessionTodayData where Id = ?";

        LessionTodayDataVO LessionTodayDataVO = new SQLiteService().runSQL(SQL, new String[] {Date}, this);
        if(LessionTodayDataVO==null){

            EnterPageFragment = new EnterPageFragment();
            FragmentTransaction FragmentTransaction = getFragmentManager().beginTransaction();
            FragmentTransaction.replace(R.id.displayPage, EnterPageFragment);
            FragmentTransaction.commit();
        }else{

            boolean ServiceOn = false;
            ActivityManager activityManager = (ActivityManager) MainActivity.this.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);

            for (ActivityManager.RunningServiceInfo runningServiceInfo : services) {
                Log.e("services：", runningServiceInfo.service.getClassName());
                if (runningServiceInfo.service.getClassName().equals(new ReminderIconService().getClass().getName())) {
                    ServiceOn = true;
                }
            }

            if (ServiceOn) {
                //ReminderIconService.changeImage(Percentage); 有兩個 一個放在連接時 一個放在這邊 因為連接成功只會跑一次.... 所以需要Try
                try{

                //如果Service存在, onbind更新其圖片 首先設定連接口
                if (SeverConnection==null){
                setServiceConnection();}

                Intent intent = new Intent(MainActivity.this, ReminderIconService.class);
                bindService(intent, SeverConnection, Context.BIND_AUTO_CREATE);

                Percentage = LessionTodayDataVO.getPercentage();
                    ReminderIconService.changeImage(Percentage);

                }catch (Exception e){

                }

            }
            //測試contentPage fragment OK
        ContentPageFragment = new ContentPageFragment();
        FragmentTransaction FragmentTransaction = getFragmentManager().beginTransaction();
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

    public void serviceDisconnect(){
        try{
        MainActivity.this.unbindService(SeverConnection);
        SeverConnection = null;
        }catch (Exception e){

        }
    }
    public void setServiceConnection(){

          SeverConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName className, IBinder service) {
                ReminderIconService.LocalBinder binder = (ReminderIconService.LocalBinder) service;
                ReminderIconService = binder.getService();

                ReminderIconService.changeImage(Percentage);

                Log.e("onServiceConnected","onServiceConnectedonServiceConnectedonServiceConnected");
            }

            public void onServiceDisconnected(ComponentName arg0) {
                Log.e("onServiceDisconnected","onServiceDisconnectedonServiceDisconnectedonServiceDisconnected");
            }

        };
    }


}
