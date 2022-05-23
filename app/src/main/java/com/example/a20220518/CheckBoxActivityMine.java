package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class CheckBoxActivityMine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box_mine);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("CheckBox");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        CheckBox checkBox_1=findViewById(R.id.my_check_box);
        checkBox_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(CheckBoxActivityMine.this, "你进行了勾选", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CheckBoxActivityMine.this, "你取消了勾选", Toast.LENGTH_SHORT).show();
                }

            }
        });

        CheckBox checkBox_2=findViewById(R.id.my_check_box2);
        checkBox_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(CheckBoxActivityMine.this, "你进行了勾选", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CheckBoxActivityMine.this, "你取消了勾选", Toast.LENGTH_SHORT).show();
                }

            }
        });
        CheckBox checkBox_3=findViewById(R.id.my_check_box3);
        checkBox_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(CheckBoxActivityMine.this, "你进行了勾选", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CheckBoxActivityMine.this, "你取消了勾选", Toast.LENGTH_SHORT).show();
                }

            }
        });
        CheckBox checkBox_4=findViewById(R.id.my_check_box4);
        checkBox_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(CheckBoxActivityMine.this, "你进行了勾选", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CheckBoxActivityMine.this, "你取消了勾选", Toast.LENGTH_SHORT).show();
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
//
                startActivity(new Intent(CheckBoxActivityMine.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/xiao_yuanjl/article/details/78386242?ops_request_misc=" +
                        "%257B%2522request%255Fid%2522%253A%2522165311302816781683927165%2522%252C%252" +
                        "2scm%2522%253A%252220140713.130102334..%2522%257D&request_id=165311302816781" +
                        "683927165&biz_id=0&utm_medium=distribute.pc_search_resu" +
                        "lt.none-task-blog-2~all~sobaiduend~default-2-78386242-null-null.142^v10^c" +
                        "ontrol,157^v4^control&utm_term=安卓CheckBox&spm=1018.222" +
                        "6.3001.4187").putExtra("title","CheckBox"));
                return false;

            }
        });



        menu.add(0, 1, 2, "3D效果").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//
                startActivity(new Intent(CheckBoxActivityMine.this,ImageView2Activity.class));
                return false;

            }
        });



        menu.add(0, 2, 3, "取消");

        return super.onCreateOptionsMenu(menu);

    }
}