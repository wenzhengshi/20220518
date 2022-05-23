package com.example.a20220518.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.a20220518.R;
import com.example.a20220518.ServiceActivity;

public class SimpleService extends Service {
    public SimpleService() {
    }









    /**
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。该方法只被调用一次
     */
    @Override
    public void onCreate() {

        System.out.println("onCreate invoke");
        super.onCreate();

//
//            Intent intent=new Intent(getApplicationContext(),ServiceActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);

    }

    /**
     * 每次通过startService()方法启动Service时都会被回调。
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "service已启动", Toast.LENGTH_SHORT).show();


        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder ;
        String CHANNEL_ID = "my_channel_01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){        //Android 8.0适配
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);//如果这里用IMPORTANCE_NOENE就需要在系统的设置里面开启渠道， //通知才能正常弹出
            manager.createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(this,String.valueOf(CHANNEL_ID));
        }else{
            builder = new NotificationCompat.Builder(this);
        }
        builder.setContentTitle("启动了service")            //指定通知栏的标题内容
                .setContentText("点击查看")             //通知的正文内容
                .setWhen(System.currentTimeMillis())                //通知创建的时间
                .setSmallIcon(R.drawable.ic_launcher_background)    //通知显示的小图标，只能用alpha图层的图片进行设置
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background))
//                .setAutoCancel(true)//设置自动关闭
                .setOnlyAlertOnce(true);// 同一个通知 播放首次声音和振动
//                .setProgress(100, 20, false);
        //跳转
        Intent intent2 = new Intent(this,ServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        try {
            // Perform the operation associated with our pendingIntent
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
        builder.setContentIntent(pendingIntent);




        Notification notification = builder.build() ;
        notification.flags = Notification.FLAG_AUTO_CANCEL;

//        Notification.FLAG_SHOW_LIGHTS              //三色灯提醒，在使用三色灯提醒时候必须加该标志符
//        Notification.FLAG_ONGOING_EVENT          //发起正在运行事件（活动中）
//        Notification.FLAG_INSISTENT             //让声音、振动无限循环，直到用户响应 （取消或者打开）
//        Notification.FLAG_ONLY_ALERT_ONCE      //发起Notification后，铃声和震动均只执行一次
//        Notification.FLAG_AUTO_CANCEL          //用户单击通知后自动消失
//        Notification.FLAG_NO_CLEAR              //只有代码调用全部清除时，Notification才会清除
//        Notification.FLAG_FOREGROUND_SERVICE    //表示正在运行的服务


        startForeground(1,notification);
        System.out.println("onStartCommand invoke");

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 服务销毁时的回调
     */
    @Override
    public void onDestroy() {
        System.out.println("onDestroy invoke");
        Toast.makeText(getApplicationContext(), "service已关闭", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }



    /**
     * 绑定服务时才会调用
     * 必须要实现的方法
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}