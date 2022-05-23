package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class ImageSwitherActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_add;
    private Button button_jian;
    private ImageSwitcher imageSwitcher;
    private List<Drawable> list;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_swither);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("视图切换ImagSwitcher");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        button_add = (Button) findViewById(R.id.button1);
        button_jian = (Button) findViewById(R.id.button2);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);

        button_add.setOnClickListener(this);
        button_jian.setOnClickListener(this);


        putData();
        //通过代码设定切换效果
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(ImageSwitherActivity.this, android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(ImageSwitherActivity.this, android.R.anim.fade_out));
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {
                // makeView返回的是当前需要显示的ImageView控件，用于填充进ImageSwitcher中。
                ImageView image = new ImageView(ImageSwitherActivity.this);
                image.setMinimumHeight(200);
                image.setMinimumWidth(200);
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                image.setLayoutParams(new ImageSwitcher.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                return image;
            }
        });
        imageSwitcher.setImageDrawable(list.get(0));

    }

    private void putData() {
        //填充图片的Drawable资源数组
        list = new ArrayList<Drawable>();
        list.add(getResources().getDrawable(R.drawable.img5));
        list.add(getResources().getDrawable(R.drawable.img3));
        list.add(getResources().getDrawable(R.drawable.img2));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:

                index--;
                if (index < 0) {
                    //用于循环显示图片
                    index = list.size() - 1;
                }
                //设定ImageSwitcher显示新图片
                imageSwitcher.setImageDrawable(list.get(index));
                break;

            case R.id.button2:
                index++;
                if (index >= list.size()) {
                    //用于循环显示图片
                    index = 0;
                }
                imageSwitcher.setImageDrawable(list.get(index));
                break;
        }
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
                startActivity(new Intent(ImageSwitherActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/leol_2/article/details/78624185?ops_" +
                        "request_misc=%257B%2522request%255Fid%2522%253A%25221653179" +
                        "41816780366526841%2522%252C%2522scm%2522%253A%252220140713." +
                        "130102334..%2522%257D&request_id=165317941816780366526841&" +
                        "biz_id=0&utm_medium=distribute.pc_search_result.none-task-bl" +
                        "og-2~all~sobaiduend~default-1-78624185-null-null.142^v10^" +
                        "control,157^v4^control&utm_term=imageSwitcher&spm=1018.2226.3001.4187").putExtra("title","视图切换ImagSwitcher"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }
}