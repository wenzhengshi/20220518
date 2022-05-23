package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class AbsoluteLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absolute_layout);
        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("AbsoluteLayout（绝对布局）");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        findViewById(R.id.absoluteLayout_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.absoluteLayout_btn).setEnabled(false);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(AbsoluteLayout.this);
                //setTitle的话，文本会显示不全
                alertDialog.setMessage(" 我的属性： android:layout_x=\"20dp\"\n" +
                        "        android:layout_y=\"200dp\"\n" +
                        "        android:text=\"被废弃的absoluteLayout\"\n" +
                        "        android:layout_width=\"wrap_content\"\n" +
                        "        android:layout_height=\"wrap_content\"");


                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(ConstraintLayout_Mine.this,"点击了确认",Toast.LENGTH_LONG).show();
                        findViewById(R.id.absoluteLayout_btn).setEnabled(true);
                    }
                });
                alertDialog.setCancelable(false);//防止按下返回键就关闭弹框
                alertDialog.show();
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
                startActivity(new Intent(AbsoluteLayout.this,HrefWebView.class).putExtra("url",
                        "https://blog.csdn.net/ll_j_21/article/details/112" +
                                "305187?ops_request_misc=%257B%2522request%255Fid%" +
                                "2522%253A%2522165286187816782425128158%2522%252C%2522scm%" +
                                "2522%253A%252220140713.130102334..%2522%257D&request_id=1652" +
                                "86187816782425128158&biz_id=0&utm_medium=distribute.pc_searc" +
                                "h_result.none-task-blog-2~all~sobaiduend~default-2-112305187-" +
                                "null-null.142^v10^control,157^v4^control&utm_term=absolu" +
                                "teLayout&spm=1018.2226.3001.4187").putExtra("title","AbsoluteLayout(绝对布局)"));

                return false;

            }
        });
        // 相当于在res/menu/main.xml文件中，给menu增加一个新的条目item，这个条目会显示title标签的文字(如备注1)

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }

}