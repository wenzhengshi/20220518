package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteTexviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_texview);
        ActionBar ac = getSupportActionBar();
        if (ac != null) {
            ac.setTitle("AutoCompleteTextview");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        AutoCompleteTextView tv=null;
        tv=findViewById(R.id.auto);
        String []str=new String[]{"China","Japan","Korean","Russian","USA","Hong Kong","中国","日本"};
        tv.setThreshold(1);// 输入一个字母就开始自动提示
        ArrayAdapter aaa=new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, str);
        tv.setAdapter(aaa);




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
                startActivity(new Intent(AutoCompleteTexviewActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/u012338845/article/details/39577971?spm=1001.2101.3001.6" +
                        "650.2&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7" +
                        "ERate-2-39577971-blog-98582642.pc_relevant_antiscanv3&depth_1-utm_sourc" +
                        "e=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-2-395779" +
                        "71-blog-98582642.pc_relevant_antiscanv3&utm_relevant_index=5").putExtra("title","AutoCompleteTextView"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }


}