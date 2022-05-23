package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class OptionMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);
        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("ContextMenu");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
    }

    //用于创建选项菜单的事件方法，在打开界面时会被自动调用
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //getMenuInflater（），获取一个菜单填充器
        getMenuInflater().inflate(R.menu.menu_main, menu);


        menu.add(0, 0, 1, "查看教程").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//
                startActivity(new Intent(OptionMenuActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/lu202032/article/details/117989863?ops_request_misc=&request_id=&biz_id=102&utm_t" +
                        "erm=contextMenu&utm_medium=distribute.pc_sea" +
                        "rch_result.none-task-blog-2~all~sobaiduweb~default-3-11" +
                        "7989863.nonecase&spm=1018.2226.3001.4187").putExtra("title","OptionMenu"));
                return false;

            }
        });

        //添加菜单项（组的ID，当前选项ID，排序编号，名称） 每一个为一个MenuItemd对象
     /*   menu.add(0, 100, 1, "菜单一");
        menu.add(0, 200, 2, "菜单二");
        menu.add(0, 300, 3, "菜单三");
        menu.add(0, 400, 4, "菜单四");
        menu.add(0, 500, 5, "Settings");*/

        return true;
    }


    //菜单选项的单击事件处理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        switch (id) {

            case R.id.one:
                Toast.makeText(this, "打开菜单一", Toast.LENGTH_SHORT).show();
                break;
            case R.id.two:
                Toast.makeText(this, "打开菜单二", Toast.LENGTH_SHORT).show();
                break;
            case R.id.three:
                Toast.makeText(this, "打开菜单三", Toast.LENGTH_SHORT).show();
                break;
            case R.id.four:
                Toast.makeText(this, "打开菜单四", Toast.LENGTH_SHORT).show();
                break;
            case R.id.set:
                Toast.makeText(this, "打开设置", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                this.finish();
                return true;



        }

        return super.onOptionsItemSelected(item);
    }


}
