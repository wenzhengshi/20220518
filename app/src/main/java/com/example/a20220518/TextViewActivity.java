package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TextViewActivity extends AppCompatActivity {
    private TextView notice_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("TextView各种样式");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        String message = "单击打开 <a href='http://www.baidu.com/'>百度首页</a>";
        notice_count =findViewById(R.id.notice_count);
        notice_count.setText(Html.fromHtml(message));
        // 这行代码是必须的
        notice_count.setMovementMethod(LinkMovementMethod.getInstance());

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
                startActivity(new Intent(TextViewActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/speverr" +
                        "iver/article/details/51065514?ops" +
                        "_request_misc=%257B%2522request%25" +
                        "5Fid%2522%253A%25221653065981167814" +
                        "32958168%2522%252C%2522scm%2522%253" +
                        "A%252220140713.130102334..%2522%257D" +
                        "&request_id=165306598116781432958168" +
                        "&biz_id=0&utm_medium=distribute.pc_search_result." +
                        "none-task-blog-2~all~so" +
                        "baiduend~default-1-51065514-null-nu" +
                        "ll.142^v10^control,157^v4^control&" +
                        "utm_term=圆形textView&spm=101" +
                        "8.2226.3001.4187").putExtra("title","TextView详解"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }

}