package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TimePickerActivity extends AppCompatActivity {
    TimePicker timePicker; /* 定义时间选择器 */
    int hour, minute; /* 定义小时和分 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picke_activty);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("时间选择器TimePicker");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        timePicker = (TimePicker) findViewById(R.id.my_time_picker);
        timePicker.setIs24HourView(true); /* 设置时间为24小时制 */
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY); /* 获取当前小时 */
        minute = calendar.get(Calendar.MINUTE); /* 获取当前分钟 */
        /* 为时间选择器设置监听器 */
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                TimePickerActivity.this.hour = hourOfDay; /* 改变小时后的参数 */
                TimePickerActivity.this.minute = minute; /* 改变分钟后的参数 */
                show(hourOfDay, minute);
            }

            private void show(int hourOfDay, int minute) {
                String str = hourOfDay + "时" + minute + "分";
                Toast.makeText(TimePickerActivity.this, str, Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(TimePickerActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/fukangwei_lite/article/details/1178" +
                        "09456?ops_request_misc=%257B%2522request%255Fid%2522%253A%" +
                        "2522165317477916782184626901%2522%252C%2522scm%2522%253A%25" +
                        "2220140713.130102334..%2522%257D&request_id=165317477916782" +
                        "184626901&biz_id=0&utm_medium=distribute.pc_search_result.no" +
                        "ne-task-blog-2~all~top_click~default-1-117809456-null-null." +
                        "142^v10^control,157^v4^control&utm_term=timePicker" +
                        "&spm=1018.2226.3001.4187").putExtra("title","TimePicker时间选择器"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }
}