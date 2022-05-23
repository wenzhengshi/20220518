package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ScrollViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("ScrollView");
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
                startActivity(new Intent(ScrollViewActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/qq_36243942/article/details/82185051?ops_" +
                        "request_misc=%257B%2522request%255Fid%2522%253A%2522165294563316782" +
                        "248516242%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&requ" +
                        "est_id=165294563316782248516242&biz_id=0&utm_medium=distribute.pc_search_r" +
                        "esult.none-task-blog-2~all~top_posit" +
                        "ive~default-1-82185051-null-null.142^v10^control," +
                        "157^v4^control&utm_term=Scrollview&spm=1018.2226.3001.4187").putExtra("title","ScrollView详解"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }
}
