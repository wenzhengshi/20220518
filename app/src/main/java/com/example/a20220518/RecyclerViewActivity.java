package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter mMyAdapter ;
    List<News> mNewsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("RecyclerView");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }



        mRecyclerView = findViewById(R.id.recycler_view);
        // 构造一些数据
        for (int i = 0; i < 50; i++) {
            News news = new News();
            news.title = "标题" + i;
            news.content = "内容" + i;
            mNewsList.add(news);
        }
        mMyAdapter = new MyAdapter();

        mRecyclerView.setAdapter(mMyAdapter);
        //分割线
        DividerItemDecoration mDivider = new
                DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(mDivider);
        DefaultItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);

        LinearLayoutManager layoutManager = new LinearLayoutManager(RecyclerViewActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);

//        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                News news = new News();
                news.title = "标题 新内容" ;
                news.content = "内容 新内容" ;
                mNewsList.add(1,news);
                mMyAdapter.notifyItemInserted(1);
            }
        });
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewsList.remove(0);
                mMyAdapter.notifyItemMoved(0,1);
            }
        });

        RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(500/*,false*/);//传入false表示刷新失败
                mNewsList.clear();
                for (int i = 0; i < 10; i++) {
                    News news = new News();
                    news.title = "标题 新内容" + i;
                    news.content = "内容" + i;
                    mNewsList.add(news);
                }
                mMyAdapter.notifyDataSetChanged();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(500/*,false*/);//传入false表示加载失败
                for (int i = 0; i < 10; i++) {
                    News news = new News();
                    news.title = "标题 新内容" + i;
                    news.content = "内容" + i;
                    mNewsList.add(news);
                }
                mMyAdapter.notifyDataSetChanged();
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
                startActivity(new Intent(RecyclerViewActivity.this,HrefWebView.class).putExtra("url","" +
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

        menu.add(0, 1, 2, "水平的recyclerView").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//
                startActivity(new Intent(RecyclerViewActivity.this,RecyclerHorizonalViewActivity.class));
                return false;

            }

        });



        menu.add(0, 2, 3, "表格的的recyclerView").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//
                startActivity(new Intent(RecyclerViewActivity.this,RecyclerViewGridViewActivity.class));
                return false;

            }

        });


        menu.add(0, 3, 4, "取消");

        return super.onCreateOptionsMenu(menu);

    }



   private  class MyAdapter extends RecyclerView.Adapter<MyViewHoder> {

        @NonNull
        @Override
        public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(RecyclerViewActivity.this, R.layout.item_list, null);
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