package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.a20220518.entity.News;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewGridViewActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter mMyAdapter ;
    List<News> mNewsList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_grid_view_acyivity);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("表格的RecyclerView");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }


        mRecyclerView = findViewById(R.id.recyclerview_grid_view);
        // 构造一些数据
        for (int i = 0; i < 50; i++) {
            News news = new News();
            news.title = "标题" + i;
            news.content = "内容" + i;
            mNewsList.add(news);
        }
        mMyAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mMyAdapter);



        DividerItemDecoration mDivider = new
                DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(mDivider);







        GridLayoutManager layoutManager = new GridLayoutManager(RecyclerViewGridViewActivity.this,3);
//        layoutManager.setOrientation(RecyclerView.HORIZONTAL);  也能设置横向滚动
        mRecyclerView.setLayoutManager(layoutManager);




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
                startActivity(new Intent(RecyclerViewGridViewActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/huweiliyi/article" +
                        "/details/105779329?ops_request_misc=%257B" +
                        "%2522request%255Fid%2522%253A%252216530677" +
                        "3416781818757142%2522%252C%2522scm%2522%25" +
                        "3A%252220140713.130102334..%2522%257D&reque" +
                        "st_id=165306773416781818757142&biz_id=0&ut" +
                        "m_medium=distribute.pc_search_result." +
                        "none-task-blog" +
                        "-2~all~top_positive~default-1-105779329-nul" +
                        "l-null.142^v10^control,157^v4^control&utm_" +
                        "term=recyclerView&spm=1018.2226.3001.4187").putExtra("title","RecyclerView详解"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }



    private  class MyAdapter extends RecyclerView.Adapter<MyViewHoder> {

        @NonNull
        @Override
        public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(RecyclerViewGridViewActivity.this, R.layout.item_list, null);
            MyViewHoder myViewHoder = new MyViewHoder(view);
            return myViewHoder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHoder holder, int position) {
            News news = mNewsList.get(position);
            holder.mTitleTv.setText(news.title);
            holder.mTitleContent.setText(news.content);
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

    private  class MyViewHoder extends RecyclerView.ViewHolder {
        TextView mTitleTv;
        TextView mTitleContent;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            mTitleTv = itemView.findViewById(R.id.textView);
            mTitleContent = itemView.findViewById(R.id.textView2);
        }
    }

}