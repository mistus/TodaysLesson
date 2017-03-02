package mistus.com.todayslesson;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ReminderIconService extends Service {
    public ReminderIconService() {
    }

    //測試onbind
    // 蠻成功的 不刪了!
    private final LocalBinder ServiceBinder = new LocalBinder();
    private WindowManager windowManager;
    private int  Percentage;
    private Context mContext;
    private RelativeLayout reminderIcon;
    WindowManager.LayoutParams params ;

    @Override
    public void onCreate(){
        setStartForegroundAndNotification();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.e("System","Service onStartCommand");

        if(reminderIcon==null){
            Bundle extras = intent.getExtras();
            Percentage =(int)extras.get("Percentage");

        displayIcon();}
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    };

    @Override
    public IBinder onBind(Intent intent) {
        return ServiceBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
      Log.e("Service","onUnbindService");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("System","Service onDestroy");
        if(reminderIcon!=null && reminderIcon.getWindowToken() != null){
        windowManager.removeView(reminderIcon);}
        super.onDestroy();
    }

    public void displayIcon() {

        mContext = this;

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

        reminderIcon = (RelativeLayout) inflater.inflate(R.layout.reminder_icon, null);
        changeImage(Percentage);

//        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.TYPE_PHONE,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                PixelFormat.TRANSLUCENT);

         params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_TOAST,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.BOTTOM | Gravity.CENTER;
        reminderIcon.setVisibility(View.VISIBLE);
        windowManager.addView(reminderIcon, params);

//        Click to Close
//        reminderIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                params.x = params.x+50;
//                windowManager.updateViewLayout(reminderIcon, params);
//                onDestroy();
//            }
//        });
        reminderIcon.setOnTouchListener(viewMoveListener);
    }

    // OnTouch方法
    private View.OnTouchListener viewMoveListener = new View.OnTouchListener() {

        int initialX, initialY;
        float initialTouchX, initialTouchY;
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    initialX = params.x;
                    initialY = params.y;
                    initialTouchX = event.getRawX();
                    initialTouchY = event.getRawY();

                case MotionEvent.ACTION_MOVE:
                    params.x = initialX + (int) (event.getRawX() - initialTouchX);
                    params.y = initialY - (int) (event.getRawY() - initialTouchY);
                    windowManager.updateViewLayout(reminderIcon, params);

                    break;
            }
            return true;
        }
    };

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setStartForegroundAndNotification() {
//        Notification notification = new Notification();
//        //設定出現在狀態列的圖示
//        notification.icon = R.drawable.ic_launcher;
//        //顯示在狀態列的文字
//        notification.tickerText="notification on status bar.";
//        //會有通知預設的鈴聲、振動、light
//        notification.defaults=Notification.DEFAULT_ALL;
//        //設定通知的標題、內容
//        notification.setLatestEventInfo(NotificationExample.this,"Title","content",appIntent);
//        //送出Notification
//        notificationManager.notify(0,notification);
        final int notifyID = 1; // 通知的識別號碼
        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); // 取得系統的通知服務
        final Notification notification = new Notification.Builder(getApplicationContext()).
                setSmallIcon(R.drawable.ic_launcher).setContentTitle("內容標題").setContentText("內容文字").setAutoCancel(true).build(); // 建立通知
        notificationManager.notify(notifyID, notification); // 發送通知
        startForeground(notifyID, notification);

    }

    public void changeImage(int PercentageInteger){

        if(PercentageInteger>100){
            PercentageInteger = 100;}

        ImageView Icon = (ImageView)reminderIcon.findViewById(R.id.reminder_img);
        switch (PercentageInteger){
            case 0  : Icon.setImageResource(R.drawable.percentage_0); break;
            case 10 : Icon.setImageResource(R.drawable.percentage_10); break;
            case 20 : Icon.setImageResource(R.drawable.percentage_20); break;
            case 30 : Icon.setImageResource(R.drawable.percentage_30); break;
            case 40 : Icon.setImageResource(R.drawable.percentage_40); break;
            case 50 : Icon.setImageResource(R.drawable.percentage_50); break;
            case 60 : Icon.setImageResource(R.drawable.percentage_60); break;
            case 70 : Icon.setImageResource(R.drawable.percentage_70); break;
            case 80 : Icon.setImageResource(R.drawable.percentage_80); break;
            case 90 : Icon.setImageResource(R.drawable.percentage_90); break;
            case 100 :Icon.setImageResource(R.drawable.percentage_100); break;
        }
    }

    //測試onbind 內部類別
     public class LocalBinder extends Binder {
        ReminderIconService getService(){
            return ReminderIconService.this;

        }
    }
}
