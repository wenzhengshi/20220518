package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20220518.self_view_pager.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class VerticalViewPagerActivity extends AppCompatActivity {
    private ViewPager my_ViewPager;
    private List<View> mViews;  //存放视图的数组
    private View view1,view2,view3;
    private PagerAdapter mPagerAdapter;//适配器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_view_pager);
        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("ViewPager（上下滑动切换）");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        my_ViewPager=findViewById(R.id.my_viewPager_vertical);



        LayoutInflater inflater = getLayoutInflater();//获取布局对象管理
        view1=inflater.inflate(R.layout.view_pager1,null);//实例化view
        TextView text_btn=view1.findViewById(R.id.text_btn_view1);
        text_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VerticalViewPagerActivity.this, "你点击了文本", Toast.LENGTH_SHORT).show();
            }
        });
        view2=inflater.inflate(R.layout.view_pager2,null);
        view3=inflater.inflate(R.layout.view_pager3,null);


        mViews=new ArrayList<View>();//将要显示的布局存放到list数组
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);


        //实例化一个PagerAdapter的适配器
        mPagerAdapter=new PagerAdapter() {
            @Override   //返回要滑动的VIew的个数
            public int getCount() {
                return mViews.size();
            }

            @Override  //来判断pager的一个view是否和instantiateItem方法返回的object有关联
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @Override  //从当前container中删除指定位置（position）的View;
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(mViews.get(position));
            }

            @NonNull
            @Override  //第一：将当前视图添加到container中，第二：返回当前View
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(mViews.get(position));
                return mViews.get(position);
            }
        };

        my_ViewPager.setAdapter(mPagerAdapter);//设置适配器



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
                startActivity(new Intent(VerticalViewPagerActivity.this,HrefWebView.class).putExtra("url",
                        "https://blog.csdn.net/k_bb_666/article/details/79843477?ops_request_misc=%2" +
                                "57B%2522request%255Fid%2522%253A%2522165299895016781483794335%2522%252C%2" +
                                "522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=165299895" +
                                "016781483794335&biz_id=0&utm_medium=distribute.pc_search_result" +
                                ".none-task-blog-2~all~sobaiduend~default-1-79843477-null-null.142^v10^co" +
                                "ntrol,157^v4^control&utm_term=viewpager上下滑动&spm=1018.2226." +
                                "3001.4187").putExtra("title","上下滑动换的viewPager"));

                return false;

            }
        });
        // 相当于在res/menu/main.xml文件中，给menu增加一个新的条目item，这个条目会显示title标签的文字(如备注1)

        menu.add(0, 1, 2, "PagerTitleStrip").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                startActivity(new Intent(SelfActivity.this, MainActivity.class));
                //跳转PagerTitleStrip
                startActivity(new Intent(VerticalViewPagerActivity.this,PagerTitleStrip.class));

                return false;

            }
        });

        menu.add(0, 2, 3, "PagerTitleStrip").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                startActivity(new Intent(SelfActivity.this, MainActivity.class));
                //跳转PagerTitleStrip
                startActivity(new Intent(VerticalViewPagerActivity.this,PagerTitleStrip.class));

                return false;

            }
        });



        menu.add(0, 3, 4, "上下滑动的ViewPager").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                startActivity(new Intent(SelfActivity.this, MainActivity.class));
                //跳转PagerTabStrip
                startActivity(new Intent(VerticalViewPagerActivity.this,PagerTabStrip.class));

                return false;

            }
        });
        menu.add(0, 4, 5, "取消");

        return super.onCreateOptionsMenu(menu);

    }

}