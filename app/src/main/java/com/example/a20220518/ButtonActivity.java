package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("按钮Button");
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
                startActivity(new Intent(ButtonActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/myselfstarbaby/article/details/79147126?ops_request_m" +
                        "isc=&request_id=&biz_id=102&utm_term=Button&utm_medi" +
                        "um=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-0-7" +
                        "9147126.142^v10^control,157^v4^control&spm=1018.2226.3001.4187").putExtra("title","Button按钮"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }
}