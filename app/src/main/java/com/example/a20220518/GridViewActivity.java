package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.a20220518.adapter.GridViewAdapter;
import com.example.a20220518.grid_view.MyGridView;

public class GridViewActivity extends AppCompatActivity {


    private MyGridView eduGridView;
    private MyGridView visionGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("GridView");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        initViews(getWindow().getDecorView());

    }




    public void initViews(View v) {
        eduGridView = v.findViewById(R.id.my_grid_view1);
        String []namesEdu = {"成绩查询", "课表查询", "考试安排", "学分与绩点"};
        int []imagesEdu = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
        GridViewAdapter gridViewAdapter1 = new GridViewAdapter(GridViewActivity.this, namesEdu, imagesEdu);
        eduGridView.setAdapter(gridViewAdapter1);
        visionGridView = v.findViewById(R.id.my_grid_view2);
        String []namesVis = {"成绩占比", "分数趋势"};
        int []imagesVis = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
        GridViewAdapter gridViewAdapter2 = new GridViewAdapter(GridViewActivity.this, namesVis, imagesVis);
        visionGridView.setAdapter(gridViewAdapter2);
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
                startActivity(new Intent(GridViewActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/Cyril_KI/article/d" +
                        "etails/108422321?ops_request_misc=%257B%2522requ" +
                        "est%255Fid%2522%253A%2522165294754816781432944099" +
                        "%2522%252C%2522scm%2522%253A%252220140713.130102" +
                        "334..%2522%257D&request_id=165294754816781432944" +
                        "099&biz_id=0&utm_medium=distribute.pc_search_resu" +
                        "lt.none-task-blog-2~all~top_posit" +
                        "ive~default-1-108422321-null-null.142^v10^contr" +
                        "ol,157^v4^control&utm_term=GridView&spm=1018.2" +
                        "226.3001.4187").putExtra("title","GridView详解"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }
}
