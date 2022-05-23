package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    private NotificationManager manager;
    private  PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("Notification发送给通知");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent2 = new Intent(NotificationActivity.this,ServiceActivity.class);
         pendingIntent = PendingIntent.getActivity(NotificationActivity.this, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);

            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
        }

        Button chat = findViewById(R.id.chat);
        chat.setOnClickListener(this);
        Button get = findViewById(R.id.get);
        get.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.chat:    //聊天消息
                Notification notification = new NotificationCompat.Builder(this, "chat")
                        .setAutoCancel(true)
                        .setContentTitle("收到聊天消息")
                        .setContentText("今天晚上吃什么")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        //设置红色
                        .setColor(Color.parseColor("#F00606"))
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)
                        .build();
                manager.notify(1, notification);
                break;
            case R.id.get:   //订阅消息
                Notification notificationGet = new NotificationCompat.Builder(this, "subscribe")
                        .setAutoCancel(true)
                        .setContentTitle("收到订阅消息")
                        .setContentText("新闻消息")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)
                        .build();
                manager.notify(2, notificationGet);
                break;
        }
    }

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
                startActivity(new Intent(NotificationActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/qq_35507234/article/details/90676587?ops_" +
                        "request_misc=%257B%2522request%255Fid%2522%253A%252216531825881" +
                        "6781818713833%2522%252C%2522scm%2522%253A%252220140713.13010233" +
                        "4..%2522%257D&request_id=165318258816781818713833&biz_id=0&utm_m" +
                        "edium=distribute.pc_search_result.none-tas" +
                        "k-blog-2~all~top_positive~default-1-90676587-null-null.142^v10^c" +
                        "ontrol,157^v4^control&utm_term=notification&spm=1018" +
                        ".2226.3001.4187").putExtra("title","Notification发送通知"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }

}