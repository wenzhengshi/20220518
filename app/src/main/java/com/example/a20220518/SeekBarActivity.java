package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SeekBarActivity extends AppCompatActivity {

    private SeekBar mSeekBar;
    private TextView mTextView;
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("seekBar拖动条");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        mSeekBar=findViewById(R.id.seek_bar);
        mTextView=findViewById(R.id.tv_progress);
        mImageView=findViewById(R.id.iv_zhuyin);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override  //当滑块进度改变时，会执行该方法下的代码
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mImageView.setAlpha(i);//设置当前的透明度
                mTextView.setText("当前透明度： " +i+"/255");
            }

            @Override  //当开始滑动滑块时，会执行该方法下的代码
            public void onStartTrackingTouch(SeekBar seekBar) {

                Toast.makeText(SeekBarActivity.this,"我seekbar开始滑动了", Toast.LENGTH_SHORT).show();
            }

            @Override   //当结束滑动滑块时，会执行该方法下的代码
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(SeekBarActivity.this,"我seekbar结束滑动了",Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(SeekBarActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/Mq_sir/article/details/116903881?o" +
                        "ps_request_misc=%257B%2522request%255Fid%2522%253A%2522" +
                        "165318696916782425172271%2522%252C%2522scm%2522%253A%25" +
                        "2220140713.130102334..%2522%257D&request_id=16531869691" +
                        "6782425172271&biz_id=0&utm_medium=distribute.pc_search_resul" +
                        "t.none-task-blog-2~all~top_click~default-1-116903881-nu" +
                        "ll-null.142^v10^control,157^v4^control&utm_term=s" +
                        "eekBar&spm=1018.2226.3001.4187").putExtra("title","seekBar拖动条"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }



}