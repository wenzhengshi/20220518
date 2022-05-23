package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class SwipeRefreshLayout_Mine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout_mine);
        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle(" SwipeRefreshLayout下拉刷新布局");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        TextView swipe_text=findViewById(R.id.swipe_refresh_text);
        SwipeRefreshLayout swipeRefreshLayout=findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                try {

                    Thread.sleep(2000);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    Toast.makeText(SwipeRefreshLayout_Mine.this, "抱歉出错了", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }finally {
                    swipeRefreshLayout.setRefreshing(false);
                    swipe_text.setText("数据已更新"+Math.round((Math.random()+1) * 1000));
                }
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
//                startActivity(new Intent(SelfActivity.this, MainActivity.class));
                //跳转网页教程
                startActivity(new Intent(SwipeRefreshLayout_Mine.this,HrefWebView.class).putExtra("url",
                        "https://blog.csdn.net/asi" +
                                "alyf/article/details/79622959?op" +
                                "s_request_misc=&request_id=&biz_id=102&utm" +
                                "_term=是wipeRefresh%20Layout、&utm_medium=distribute.pc_sear" +
                                "ch_result.none-task-" +
                                "blog-2~all~sobaiduweb~default-6-79622959." +
                                "142^v10" +
                                "^control,157^v4^control&spm=1018.2226.3" +
                                "001.4187").putExtra("title","SwipeRefreshLayout下拉刷新布局"));

                return false;

            }
        });
        // 相当于在res/menu/main.xml文件中，给menu增加一个新的条目item，这个条目会显示title标签的文字(如备注1)

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }
}