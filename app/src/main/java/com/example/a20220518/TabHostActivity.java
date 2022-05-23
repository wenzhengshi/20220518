package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class TabHostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("TabHost书签选项(过时)");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        TabHost th=findViewById(R.id.tabhost);
        th.setup();            //初始化TabHost容器

        //在TabHost创建标签，然后设置：标题／图标／标签页布局
        th.addTab(th.newTabSpec("tab1").setIndicator("标签1",getResources().getDrawable(R.drawable.img5)).setContent(R.id.tab1));
        th.addTab(th.newTabSpec("tab2").setIndicator("标签2",null).setContent(R.id.tab2));
        th.addTab(th.newTabSpec("tab3").setIndicator("标签3",null).setContent(R.id.tab3));
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
                startActivity(new Intent(TabHostActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/zhupengqq/article/details/51594323?ops_request_misc=%257B%2" +
                        "522request%255Fid%2522%253A%2522165318417416780366511591%2522%252C%2522scm%2522" +
                        "%253A%252220140713.130102334..%2522%257D&request_id=165318417416780366511591&b" +
                        "iz_id=0&utm_medium=distribute.pc_search_re" +
                        "sult.none-task-blog-2~all~sobaiduend~default-1-51594323-null-null.142^v10^co" +
                        "ntrol,157^v4^control&utm_term=tabhost&spm=1018.2226.3001.4187").putExtra("title","TabHost书签选项"));
                return false;

            }
        });

        menu.add(0, 0, 1, "TabHostActivity").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//
                startActivity(new Intent(TabHostActivity.this,TabHostActivity2.class));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }

}