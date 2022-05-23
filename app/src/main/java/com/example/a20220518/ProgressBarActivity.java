package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("ProgressBar进度条");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

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
                startActivity(new Intent(ProgressBarActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/xqhys/article/details/9931" +
                        "4998?ops_request_misc=&request_id=&biz_id=102&ut" +
                        "m_term=ProgressBar&utm_" +
                        "medium=distribute.pc_search_result.none-task-b" +
                        "log-2~all~sobaiduweb~default-4-99314998.142^v10^" +
                        "control,157^v4^control&spm=1018.2226.3001.4187").putExtra("title","ProgressBar进度条"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }




}