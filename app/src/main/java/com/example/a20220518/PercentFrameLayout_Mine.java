package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PercentFrameLayout_Mine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent_frame_layout_mine);
        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("PercentFrameLayout（百分比布局[已放弃]）");
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
//                startActivity(new Intent(SelfActivity.this, MainActivity.class));
                //跳转网页教程
                startActivity(new Intent(PercentFrameLayout_Mine.this,HrefWebView.class).putExtra("url",
                        "https://blog.csdn.net/android_studying/a" +
                                "rticle/details/85935240?ops_request_" +
                                "misc=%257B%2522request%255Fid%2522%2" +
                                "53A%2522165286471016782246461315%2522%252" +
                                "C%2522scm%2522%253A%252220140713.130102334..%" +
                                "2522%257D&request_id=165286471016782246461315&b" +
                                "iz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~" +
                                "all~sobaiduend~default-1-85935240-n" +
                                "ull-null.142^v10^control,157^v4^control&utm_te" +
                                "rm=PercentFrameLayout&spm=1018.2226.3001.4187").putExtra("title","百分比布局PercentFrameLayout"));

                return false;

            }
        });
        // 相当于在res/menu/main.xml文件中，给menu增加一个新的条目item，这个条目会显示title标签的文字(如备注1)

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }


}