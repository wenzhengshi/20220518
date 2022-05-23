package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20220518.adapter.ImageAdapter;
import com.example.a20220518.img_view_3d.MyGallery;

public class ImageView2Activity extends AppCompatActivity {


    private TextView tvTitle;
    private MyGallery gallery;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view2);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("ImageView");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        initRes();
    }

    private void initRes(){
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        gallery = (MyGallery) findViewById(R.id.mygallery);     // 获取自定义的myGallery控件

        adapter = new ImageAdapter(this);
        adapter.createReflectedImages();    // 创建倒影效果
        gallery.setAdapter(adapter);

        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {    // 设置选择事件监听
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvTitle.setText(adapter.titles[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {          // 设置点击事件监听
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ImageView2Activity.this, "img " + (position+1) + " selected", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(ImageView2Activity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/zd_1471278687/article/details/13998413").putExtra("title","3DImageView"));
                return false;

            }
        });



        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }

}