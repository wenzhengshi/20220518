package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PopupWindowActivity extends AppCompatActivity {
    private static final String TAG = "PopupWindowTest";
    private ListView list_pop;
    private Button open;
    private List<Map<String, Object>> list;
    private SimpleAdapter sAdapter;
    private PopupWindow popup;

    private String[] names = new String[] { "鼠", "牛", "虎", "兔", "龙" };
    private int[] imag = new int[] { R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background, R.drawable.ic_launcher_background };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("PopupWindow列表弹窗");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        popup();
    }

    DisplayMetrics metrics = new DisplayMetrics();
    final int sWidth = metrics.widthPixels;
    final int sHeight = metrics.heightPixels;

    public void list(View v) {
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("names", names[i]);
            map.put("imag", imag[i]);
            list.add(map);
        }
        sAdapter = new SimpleAdapter(PopupWindowActivity.this, list,
                R.layout.item_popup, new String[] { "names", "imag" },
                new int[] { R.id.tv_item, R.id.iv_item });
        list_pop = (ListView)v.findViewById(R.id.list_pop);
        //从不同于主xml(setContentView(R.layout.main);)中使用其他xml布局中的控件时，需声明其所在的VIew布局。
        list_pop.setAdapter(sAdapter);

        list_pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.i(TAG, "你点击了：" + names[position]);
            }
        });
    }

    public void popup() {
        // 装载R.layout.popup对应的界面布局
        View root = this.getLayoutInflater().inflate(R.layout.popup, null);
        // 创建PopupWindow对象
        popup = new PopupWindow(root, 400, 800);
        open = (Button) findViewById(R.id.btn_open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 以下拉方式显示。
                popup.showAtLocation(findViewById(R.id.btn_open), Gravity.TOP
                        | Gravity.RIGHT, sWidth, 200);
            }
        });
        list(root);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            popup.dismiss();
        }
        return super.onTouchEvent(event);
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
                startActivity(new Intent(PopupWindowActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/jeromexiong/article/details/481" +
                        "79569?spm=1001.2101.3001.6650.15&utm_medium=distribute" +
                        ".pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendF" +
                        "romBaidu%7Edefault-15-48179569-blog-53213705.pc_relevan" +
                        "t_default&depth_1-utm_source=distribute.p" +
                        "c_relevant.none-task-blog-2%7Edefault%7EBlogCommendFr" +
                        "omBaidu%7Edefault-15-48179569-blog-53213705.pc_relevant_" +
                        "default&utm_relevant_index=19").putExtra("title","PopupWindow列表弹窗"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }



}