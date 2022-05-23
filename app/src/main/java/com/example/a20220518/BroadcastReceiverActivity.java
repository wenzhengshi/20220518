package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a20220518.broad_cast_receiver.MyBroadcastReceiver;
import com.example.a20220518.save.SharedP;

import java.util.Locale;

public class BroadcastReceiverActivity extends AppCompatActivity {



        private MyBroadcastReceiver receiver;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_broadcast_receiver);
            //设置ActionBar
            ActionBar ac=getSupportActionBar();
            if(ac!=null){
                ac.setTitle("广播接受者BroadcastReceiver");
                ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

            }
//            registerMyReceiver();//在activity创建的时候进行注册监听
            Button my_broad_cast_btn=findViewById(R.id.my_broad_cast_btn);
            Button my_broad_cast_btn_close=findViewById(R.id.my_broad_cast_btn_close);
            if(receiver!=null) {
                if (receiver.isOrderedBroadcast()) {
                    //按钮状态管理
                    my_broad_cast_btn.setEnabled(false);
                    my_broad_cast_btn_close.setEnabled(true);
                }
            }
            //显示最近的一侧熄屏和开屏的时间
            TextView show_broad_cast_state=findViewById(R.id.show_broadcast_state);
            SharedP sp=new SharedP(BroadcastReceiverActivity.this,"20220518");
            if(sp!=null&&sp.getValue("broad_cast_sate")!=null){
                show_broad_cast_state.setText("此广播监听熄灭和开启屏幕，你最近一次开屏或熄灭屏幕的时间是："+sp.getValue("broad_cast_sate"));
            }

            my_broad_cast_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //开启广播
                    my_broad_cast_btn.setEnabled(false);
                    my_broad_cast_btn_close.setEnabled(true);
                    registerMyReceiver();
                }
            });
            my_broad_cast_btn_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //关闭注销广播
                    my_broad_cast_btn_close.setEnabled(false);
                    my_broad_cast_btn.setEnabled(true);
                    unRegisterMyReceiver();

                }
            });


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
//                startActivity(new Intent(SelfActivity.this, MainActivity.class));
                //跳转网页教程
                startActivity(new Intent(BroadcastReceiverActivity.this,HrefWebView.class).putExtra("url",
                        "https://blog.csdn.net/weixin_39460667/article/details/82413819?spm=1001.2101." +
                                "3001.6650.5&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7E" +
                                "BlogCommendFromBaidu%7Edefault-5-82413819-blog-117347679.pc_relevant_defau" +
                                "lt&depth_1-utm_source=distribute.pc" +
                                "_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-5-82" +
                                "413819-blog-117347679.pc_relevant_default&utm_relevant_i" +
                                "ndex=10").putExtra("title","广播接受者BroadcastReceiver"));

                return false;

            }
        });
        // 相当于在res/menu/main.xml文件中，给menu增加一个新的条目item，这个条目会显示title标签的文字(如备注1)

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }


    private void registerMyReceiver() {
            receiver = new MyBroadcastReceiver();
            IntentFilter filter = new IntentFilter();//创建IntentFilter对象
            filter.addAction(Intent.ACTION_SCREEN_OFF);//IntentFilter对象中添加要接收的关屏广播
            filter.addAction(Intent.ACTION_SCREEN_ON);//添加点亮屏幕广播
            registerReceiver(receiver, filter);

        }

        private void unRegisterMyReceiver() {
            if (receiver != null) {
                unregisterReceiver(receiver);//反注册广播，也就是注销广播接收者，使其不起作用
            }
        }




}