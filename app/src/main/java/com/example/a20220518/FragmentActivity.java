package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("静态加载的Fragment碎片");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }


        Button to_dynamic=findViewById(R.id.to_dynamic);
        to_dynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(FragmentActivity.this, "我要看动态添加的", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FragmentActivity.this,DynamicFragmentActivty.class));
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
//                startActivity(new Intent(SelfActivity.this, MainActivity.class));
                //跳转网页教程
                startActivity(new Intent(FragmentActivity.this,HrefWebView.class).putExtra("url",
                        "https://blog.csdn.net/qq_38843126/article/details/80584187").putExtra("title","Fragment碎片"));

                return false;

            }
        });
        // 相当于在res/menu/main.xml文件中，给menu增加一个新的条目item，这个条目会显示title标签的文字(如备注1)

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }

}
