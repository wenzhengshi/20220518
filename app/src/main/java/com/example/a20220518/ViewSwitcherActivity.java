package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewSwitcher;

public class ViewSwitcherActivity extends AppCompatActivity {

    private ViewSwitcher viewSwitcher;
    private Button cut_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_switcher);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("视图切换ViewSwitcher");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        viewSwitcher=findViewById(R.id.my_view_switcher);
        cut_img=findViewById(R.id.cut_view);
        cut_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSwitcher.showNext();
//                viewSwitcher.showPrevious();
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
//
                startActivity(new Intent(ViewSwitcherActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/chennai1101/article/details/87074074" +
                        "?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165" +
                        "318074416782184686230%2522%252C%2522scm%2522%253A%2522201407" +
                        "13.130102334..%2522%257D&request_id=165318074416782184686230&" +
                        "biz_id=0&utm_medium=distribute.pc_search_result.none-" +
                        "task-blog-2~all~baidu_landing_v2~default-3-87074074-null-nul" +
                        "l.142^v10^control,157^v4^control&utm_term=viewSwitcher&spm" +
                        "=1018.2226.3001.4187").putExtra("title","视图切换ViewSwitcher"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }
}