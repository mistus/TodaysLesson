package mistus.com.todayslesson;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
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

    private WindowManager windowManager;
    private Context mContext;
//    private ReminderIcon reminderIcon;
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
        displayIcon();}
        //super
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.e("System","Service onDestroy");
        if(reminderIcon!=null && reminderIcon.getWindowToken() != null){
        windowManager.removeView(reminderIcon);}
        super.onDestroy(); }

    public void displayIcon() {

        mContext = this;

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

        reminderIcon = (RelativeLayout) inflater.inflate(R.layout.reminder_icon, null);
//        ImageView i1 = (ImageView)reminderIcon.findViewById(R.id.reminder_img);
//        i1.setImageResource(R.drawable.percentage_0);




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
}
