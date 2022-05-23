package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.a20220518.service.SimpleService;

public class ServiceActivity extends AppCompatActivity  {




    private Button startBtn;
    private Button stopBtn;
    private Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);




        //检查通栏权限是否开启
        if(NotificationManagerCompat.from(getApplicationContext()).areNotificationsEnabled()){

        }else{

            AlertDialog.Builder alertDialog=new AlertDialog.Builder(ServiceActivity.this);

            alertDialog.setMessage("你关闭了app的通知栏权限");

            alertDialog.setPositiveButton("前往打开", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    goToSetting();
                }
            });
            alertDialog.setNegativeButton("下次一定", null);
            alertDialog.show();

        }



        ActionBar ac = getSupportActionBar();
        if (ac != null) {
            ac.setTitle("Service详解");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        startBtn=findViewById(R.id.service_start);
        stopBtn= findViewById(R.id.service_stop);
         it=new Intent(ServiceActivity.this, SimpleService.class);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startBtn.setEnabled(false);
                stopBtn.setEnabled(false);
                new MyCountDownTimer(3000,1000).start();

            }
        });
        //assert stopBtn != null;
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(it);
                startBtn.setEnabled(true);
                stopBtn.setText("已停止service");
                stopBtn.setEnabled(false);
                startBtn.setText("启动service");



                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder ;
                String CHANNEL_ID = "my_channel_01";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){        //Android 8.0适配
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                            "Channel human readable title",
                            NotificationManager.IMPORTANCE_DEFAULT);//如果这里用IMPORTANCE_NOENE就需要在系统的设置里面开启渠道， //通知才能正常弹出
                    manager.createNotificationChannel(channel);
                    builder = new NotificationCompat.Builder(ServiceActivity.this,String.valueOf(CHANNEL_ID));
                }else{
                    builder = new NotificationCompat.Builder(ServiceActivity.this);
                }
                builder.setContentTitle("停止了service")            //指定通知栏的标题内容
                        .setContentText("点击查看")             //通知的正文内容
                        .setWhen(System.currentTimeMillis())                //通知创建的时间
                        .setSmallIcon(R.drawable.ic_launcher_background)    //通知显示的小图标，只能用alpha图层的图片进行设置
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background))
//                .setAutoCancel(true)//设置自动关闭
                        .setOnlyAlertOnce(true);// 同一个通知 播放首次声音和振动
//                .setProgress(100, 20, false);
                //跳转
                Intent intent2 = new Intent(ServiceActivity.this,ServiceActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(ServiceActivity.this, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
                try {
                    // Perform the operation associated with our pendingIntent
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
                builder.setContentIntent(pendingIntent);




                Notification notification = builder.build() ;
                notification.flags = Notification.FLAG_AUTO_CANCEL;
                manager.notify(1,notification);


//        Notification.FLAG_SHOW_LIGHTS              //三色灯提醒，在使用三色灯提醒时候必须加该标志符
//        Notification.FLAG_ONGOING_EVENT          //发起正在运行事件（活动中）
//        Notification.FLAG_INSISTENT             //让声音、振动无限循环，直到用户响应 （取消或者打开）
//        Notification.FLAG_ONLY_ALERT_ONCE      //发起Notification后，铃声和震动均只执行一次
//        Notification.FLAG_AUTO_CANCEL          //用户单击通知后自动消失
//        Notification.FLAG_NO_CLEAR              //只有代码调用全部清除时，Notification才会清除
//        Notification.FLAG_FOREGROUND_SERVICE    //表示正在运行的服务





            }
        });
        //设置ActionBar



    }
//
//    @Override
//    public void onClick(View v) {
//        Intent it=new Intent(this, SimpleService.class);
//        switch (v.getId()){
//            case R.id.service_start:
//
//                    stopBtn.setText("停止service");
//                    myCountDownTimer = new MyCountDownTimer(10000,1000);
//                    myCountDownTimer.start();
//                startService(it);
//                break;
//            case R.id.service_stop:
//                stopService(it);
//                stopBtn.setText("已停止service");
//                break;
//        }
//    }







    // 监听返回按钮，如果点击返回按钮则关闭当前Activity
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == android.R.id.home) {
                this.finish();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            menu.add(0, 0, 1, "查看教程").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
//
                    startActivity(new Intent(ServiceActivity.this,HrefWebView.class).putExtra("url","" +
                            "https://blog.csdn.net/hdhhd/article/details/80612726?ops_request_misc=&request_id=&biz_id=" +
                            "102&utm_term=service&utm_medium=distribute.pc_search_result.no" +
                            "ne-task-blog-2~all~sobaiduweb~default-1-80612726.nonecase&spm=1018.2" +
                            "226.3001.4187").putExtra("title","Service详解"));
                    return false;

                }
            });

            menu.add(0, 1, 2, "取消");

            return super.onCreateOptionsMenu(menu);

        }




    //复写倒计时
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            startBtn.setText(l/1000+"秒"+"启动service");
            //防止计时过程中重复点击



        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            startBtn.setText("启动service");
            startBtn.setEnabled(false);
            stopBtn.setText("停止service");
            stopBtn.setEnabled(true);
            //设置可点击
            startService(it);//启动service
        }
    }


    private void goToSetting() {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 26) {// android 8.0引导
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
        } else if (Build.VERSION.SDK_INT >= 21) { // android 5.0-7.0
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", getPackageName());
            intent.putExtra("app_uid", getApplicationInfo().uid);
        } else {//其它
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getPackageName(), null));
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}