package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.a20220518.adapter.MyPagerAdapter;

import java.util.ArrayList;

public class PagerTitleStrip extends AppCompatActivity {

    private ViewPager mViewPager;
    private ArrayList<View> mViews;  //存放视图的数组
    private View view1,view2,view3;
    private MyPagerAdapter mAdapter;//适配器
    private ArrayList<String> mtitle;//存放标题的数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_title_strip);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("PagerTitleStrip");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        mViewPager = findViewById(R.id.view_pager_title_strip);// 实例化viewpager控件

        LayoutInflater inflater = getLayoutInflater();//获取布局对象管理
        view1 = inflater.inflate(R.layout.view_pager1, null);//实例化view
        view2 = inflater.inflate(R.layout.view_pager2, null);
        view3 = inflater.inflate(R.layout.view_pager3, null);

        mViews = new ArrayList<View>();//将要显示的布局存放到list数组
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);

        mtitle = new ArrayList<String>();//存放标题的数组
        mtitle.add("推荐");
        mtitle.add("热门");
        mtitle.add("直播");

        mAdapter=new MyPagerAdapter(mViews,mtitle);//实例化适配器
        mViewPager.setAdapter(mAdapter);//设置适配器
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
}
