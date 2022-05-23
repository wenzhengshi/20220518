package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {


    private TextView activity_state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        activity_state=findViewById(R.id.activity_state);
        activity_state.setText("Activity的生命周期");

        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("Activity详解");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        
    }


    @Override
    protected void onResume() {

        super.onResume();
        Toast.makeText(TestActivity.this, "历史：Activty已经被启动，已经可见，但是此时还处于后台，不能与用户进行交互", Toast.LENGTH_SHORT).show();
        activity_state.setText("Activty已经被启动，已经可见，但是此时还处于后台，不能与用户进行交互");

    }

//  activity_state.setText("");
    @Override
    protected void onPause() {
        super.onPause();
        activity_state.setText("当前Activity被其他活动的Activity覆盖时或锁屏");
        Toast.makeText(TestActivity.this, "当前Activity被其他活动的Activity覆盖时或锁屏", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onStart() {

        super.onStart();
        activity_state.setText("表示Activty已经被启动，已经可见，但是此时还处于后台，不能与用户进行交互。");
        Toast.makeText(TestActivity.this, "表示Activty已经被启动，已经可见，但是此时还处于后台，不能与用户进行交互。", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStop() {
        super.onStop();

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        activity_state.setText("当从新启动的Activity返回原来的Activity时，原来的Activity会调用此方法。表示Activity正在重新启动。");
        Toast.makeText(TestActivity.this, "当从新启动的Activity返回原来的Activity时，原来的Activity会调用此方法。表示Activity正在重新启动。", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        activity_state.setText("在Activity被销毁时调用；注意：在使用手机时会根据不同情况进行横竖屏切换。当手机横竖屏切换时，会根据清单文件AndroidManifest.xml文件中的configChanges属性不同而调用不同的生命周期\n" +
                " \n" +
                "在使用默认属性时，调用的方法顺序依次时：onCreate，onStart，onResume\n" +
                " \n" +
                "当进行横竖屏切换时，调用的方法依次时：onPause，onStop，onDestory，onCreate，onStart，onResume\n" +
                "————————————————\n" );
        Toast.makeText(TestActivity.this, "在Activity被销毁时调用；注意：在使用手机时会根据不同情况进行横竖屏切换。当手机横竖屏切换时，会根据清单文件AndroidManifest.xml文件中的configChanges属性不同而调用不同的生命周期\n" +
                " \n" +
                "在使用默认属性时，调用的方法顺序依次时：onCreate，onStart，onResume\n" +
                " \n" +
                "当进行横竖屏切换时，调用的方法依次时：onPause，onStop，onDestory，onCreate，onStart，onResume\n" +
                "————————————————\n" , Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    //



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
                startActivity(new Intent(TestActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/qq_42418169/article/details/115090626?ops_re" +
                        "uest_misc=%257B%2522request%255Fid%2522%253A%2522165290958116780366" +
                        "571262%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257" +
                        "D&request_id=165290958116780366571262&biz_id=0&utm_medium=distribute." +
                        "pc_search_result.none-task" +
                        "-blog-2~all~top_click~default-2-115090626-null-null.142^v10^control,15" +
                        "7^v4^control&utm_term=Activity&spm=1018.2226.3001.4187").putExtra("title","Activity详解"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }
}