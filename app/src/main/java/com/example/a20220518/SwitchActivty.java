package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SwitchActivty extends AppCompatActivity {
    private Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_activty);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("开关控件Switch");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        mSwitch=findViewById(R.id.my_switch_tool);
        // 添加监听
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(SwitchActivty.this, "已开启", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(SwitchActivty.this, "已关闭", Toast.LENGTH_SHORT).show();

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
                startActivity(new Intent(SwitchActivty.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/qq_30885821/article/details/117112384?ops_request_misc=%257B%2522r" +
                        "equest%255Fid%2522%253A%2522165317730616782395364024%2522%252C%2522scm%2522%253A%2522201" +
                        "40713.130102334..%2522%257D&request_id=165317730616782395364024&biz_id=0&utm_medium=dis" +
                        "tribute.pc_search_result.none-ta" +
                        "sk-blog-2~all~baidu_landing_v2~default-1-117112384-null-null.142^v10^control,157^v4^c" +
                        "ontrol&utm_term=switch开关控件&spm=1018.2226.3001.4187").putExtra("title","开关控件Switch"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }
}