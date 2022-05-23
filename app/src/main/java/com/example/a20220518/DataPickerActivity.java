package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.a20220518.time.GetNowTime;

import java.util.Calendar;

public class DataPickerActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private int year,month,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_picker);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("日期选择器DatePicker");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        //实例化控件
        datePicker= (DatePicker) findViewById(R.id.my_date_picker);
        datePicker.showContextMenu();

        //获取当前年分
        year= Calendar.getInstance().get(Calendar.YEAR);
        //获取当前月分
        month=Calendar.getInstance().get(Calendar.MONTH);
        //获取当前日期
        date=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        datePicker.init(year, month, date, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //月份要加1，相信大家都知道，就不细说了
                Toast.makeText(DataPickerActivity.this,year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日",Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(DataPickerActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/c18871189293/article/details/5" +
                        "8079255?ops_request_misc=%257B%2522request%255Fid%252" +
                        "2%253A%2522165317429216782350943663%2522%252C%2522scm" +
                        "%2522%253A%252220140713.130102334..%2522%257D&request_" +
                        "id=165317429216782350943663&biz_id=0&utm_medium=distribute.pc_search_resul" +
                        "t.none-task-blog-2~all~sobaiduend~default-3-58079255-n" +
                        "ull-null.142^v10^control,157^v4^control&utm_term=d" +
                        "ataPicker&spm=1018.2226.3001.4187").putExtra("title","DatePicker日期选择器"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }
}