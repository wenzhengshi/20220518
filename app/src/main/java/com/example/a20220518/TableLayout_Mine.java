package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TableLayout_Mine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout_mine);
        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("TableLayout表格布局");
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
                startActivity(new Intent(TableLayout_Mine.this,HrefWebView.class).putExtra("url",
                        "https://blog.csdn.net/" +
                                "m0_59586434/article/det" +
                                "ails/123637784?ops_request_misc=%25" +
                                "7B%2522request%255Fid%2522%253A%25221652883" +
                                "31716782390567939%2522%252C%2522scm%2522%253A%2" +
                                "52220140713.130102334..%2522%257D&request_id=16" +
                                "5288331716782390567939&biz_id=0&utm_medium=distr" +
                                "ibute.pc_search_result.none-task-b" +
                                "log-2~all~top_click~default-1-123637784-null-nul" +
                                "l.142^v10^control,157^v4^control&utm_term=tableLay" +
                                "out&spm=1018.2226.3001.4187").putExtra("title","TableLayout表格布局"));

                return false;

            }
        });
        // 相当于在res/menu/main.xml文件中，给menu增加一个新的条目item，这个条目会显示title标签的文字(如备注1)


        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }
}