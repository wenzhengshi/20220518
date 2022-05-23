package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.a20220518.key_board.KeyboardTEMPHelper;

public class KeyBoardActivity extends AppCompatActivity {

        private KeyboardTEMPHelper helper;
        private EditText editText;
        private KeyboardView keyboard;

        @SuppressLint("ClickableViewAccessibility")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_key_board);
            ActionBar ac=getSupportActionBar();
            if(ac!=null){
                ac.setTitle("KeyBoard");
                ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

            }
            keyboard = findViewById(R.id.keyboard_temp);
            editText = findViewById(R.id.et);
            //初始化KeyboardView
            helper = new KeyboardTEMPHelper(KeyBoardActivity.this, keyboard);
            //设置editText与KeyboardView绑定
            helper.setEditText(editText);
            helper.setCallBack(new KeyboardTEMPHelper.KeyboardCallBack() {
                @Override
                public void keyCall(int code) {
                    //回调键盘监听，根据回调的code值进行处理
                }
            });

            editText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {


                    //多条件判断，防止重复显示
                    if (!helper.isVisibility() && event.getAction() == MotionEvent.ACTION_DOWN) {
                        helper.show();
                    }
                    return false;
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
                startActivity(new Intent(KeyBoardActivity.this,HrefWebView.class).putExtra("url","https:/" +
                        "/blog.csdn.net/DeMonliuhui/article/details/84589936?ops_request_misc=%257B%2522request%255" +
                        "Fid%2522%253A%2522165317148416782395396388%2522%252C%2522scm%2522%253A%252220140713.1301023" +
                        "34.pc%255Fall.%2522%257D&request_id=165317148416782395396388&biz_id=0&utm_medium=distribu" +
                        "te.pc_search_result.none-task-blog-2" +
                        "~all~first_rank_ecpm_v1~rank_v31_ecpm-17-84589936-null-null.142^v10^control" +
                        ",157^v4^control&utm_term=安卓自定义支付键盘&spm=1018.2226.3001.4187").putExtra("title","KeyBoard"));
                return false;

            }

        });


        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }
    }

