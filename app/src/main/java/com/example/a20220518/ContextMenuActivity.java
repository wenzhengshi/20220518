package com.example.a20220518;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a20220518.time.GetNowTime;


public class ContextMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_menu;
    private TextView tv_menu;
    //声明颜色数组
    private int[] color={Color.BLACK,Color.WHITE,Color.YELLOW,
            Color.RED,Color.GREEN,Color.DKGRAY, Color.CYAN, Color.MAGENTA, Color.GRAY, Color.DKGRAY};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);
        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("ContextMenu");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        btn_menu=findViewById(R.id.btn_menu);
        tv_menu=findViewById(R.id.tv_menu);
        btn_menu.setOnClickListener(this);
        ctime();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_menu){
            //1.给按钮btn_menu先注册上下文菜单
            registerForContextMenu(v);
            //2.显示打开上下文菜单
            openContextMenu(v);
            //3.注销上下文菜单
            unregisterForContextMenu(v);
        }
    }

    //在页面恢复时调用
    @Override
    protected void onResume() {
        //这个是给文本视图注册上下文菜单，只要长按文本视图也可以显示上下文菜单
        registerForContextMenu(tv_menu);
        super.onResume();
    }

    //给文本视图注销上下文菜单
    @Override
    protected void onPause() {
        unregisterForContextMenu(tv_menu);
        super.onPause();
    }

    //在上下文菜单界面创建时调用
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.item_context,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //获取菜单项的编号
        int id = item.getItemId();
        if (id == R.id.item_time){
            //改变时间
            ctime();
        }else if (id == R.id.item_color){
            //改变字体颜色
            tv_menu.setTextColor(changeBackground());
        }else if (id == R.id.item_background){
            //改变背景
            tv_menu.setBackgroundColor(changeBackground());
        }
        return super.onContextItemSelected(item);
    }
    //获取随机的颜色值
    private int changeBackground() {
        /**
         *Math是数学类，random（）是Math类中的一个方法，random本身只产生（0~1）之间的小数，
         *random（）*10 意思是产生0~10之间的小数 ，int(random（）*10)的意思是强制取整，把小数部分去掉只去整数部分，
         * 所以 就变成了 产生0~9之间的整数。
         */
        int random = (int) (Math.random() * 10 % 10);
        return color[random];
    }

    private void ctime() {
        tv_menu.setText(GetNowTime.getNowTime() +"这是菜单显示文本");
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
                startActivity(new Intent(ContextMenuActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/lu202032/article/details/117989863?ops_request_misc=&request_id=&biz_id=102&utm_t" +
                        "erm=contextMenu&utm_medium=distribute.pc_sea" +
                        "rch_result.none-task-blog-2~all~sobaiduweb~default-3-11" +
                        "7989863.nonecase&spm=1018.2226.3001.4187").putExtra("title","ContextMenu"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }
}

