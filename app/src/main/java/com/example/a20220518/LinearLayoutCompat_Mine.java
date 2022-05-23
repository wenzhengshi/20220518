package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LinearLayoutCompat_Mine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_compat_mine);
        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("LinearLayoutCompat（线性布局plus）");
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
                startActivity(new Intent(LinearLayoutCompat_Mine.this,HrefWebView.class).putExtra("url",
                        "https://blog.csdn.net/u013658636/article/det" +
                                "ails/51583909?ops_request_misc=%257B%2522r" +
                                "equest%255Fid%2522%253A%252216528630481678238804" +
                                "4375%2522%252C%2522scm%2522%253A%252220140713.130" +
                                "102334..%" +
                                "2522%257D&request_id=165286304816782388044375&b" +
                                "iz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~b" +
                                "aidu_landing_v2~default" +
                                "-4-51583909-null-null.142^" +
                                "v10^control,157^v4^control&utm_term" +
                                "=LinearLayoutCompat&spm=1018.2226" +
                                ".3001.4187").putExtra("title","LinearLayoutCompat(可子控件加driver)"));

                return false;

            }
        });
        // 相当于在res/menu/main.xml文件中，给menu增加一个新的条目item，这个条目会显示title标签的文字(如备注1)

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }


}