package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ConstraintLayout_Mine extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout_mine);

        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("ConstraintLayout（约束布局）");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        findViewById(R.id.my_text1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.my_text1).setEnabled(false);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(ConstraintLayout_Mine.this);

                alertDialog.setMessage("我是1(我还是链头嘞)，我的左边和2的右边对齐，我的下边和2的上边对齐，" +
                        "属性： app:layout_constraintHorizontal_chainStyle=\"spread_inside\"\n" +
                        "        app:layout_constraintBottom_toTopOf=\"@+id/my_text2\"\n" +
                        "        app:layout_constraintLeft_toRightOf=\"@+id/my_text2\"\n" +
                        "        android:text=\"1\"\n" +
                        "        android:layout_width=\"wrap_content\"\n" +
                        "        android:layout_height=\"wrap_content\"");

                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(ConstraintLayout_Mine.this,"点击了确认",Toast.LENGTH_LONG).show();
                        findViewById(R.id.my_text1).setEnabled(true);
                    }
                });
                alertDialog.setCancelable(false);//防止按下返回键就关闭弹框
                alertDialog.show();


            }
        });
        findViewById(R.id.my_text2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.my_text2).setEnabled(false);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(ConstraintLayout_Mine.this);

                alertDialog.setMessage("我是2，我的右边和1的左边对齐，我的上边和1的下边对齐， app:layout_constraintRight_toLeftOf=\"@+id/my_text1\"\n" +
                        "        app:layout_constraintBottom_toTopOf=\"@+id/my_text1\"\n" +
                        "        android:text=\"2\"\n" +
                        "        android:layout_width=\"wrap_content\"\n" +
                        "        android:layout_height=\"wrap_content\"");

                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(ConstraintLayout_Mine.this,"点击了确认",Toast.LENGTH_LONG).show();
                        findViewById(R.id.my_text2).setEnabled(true);
                    }
                });
                alertDialog.setCancelable(false);//防止按下返回键就关闭弹框
                alertDialog.show();
            }
        });
        findViewById(R.id.my_text3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.my_text3).setEnabled(false);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(ConstraintLayout_Mine.this);

                alertDialog.setMessage("我是3，我的左边和2的右边边对齐，我的上边和2的下边对齐，app:layout_constraintLeft_toRightOf=\"@+id/my_text2\"\n" +
                        "        app:layout_constraintTop_toBottomOf=\"@+id/my_text2\"\n" +
                        "        android:text=\"3\"\n" +
                        "        android:layout_width=\"wrap_content\"\n" +
                        "        android:layout_height=\"wrap_content\"");

                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(ConstraintLayout_Mine.this,"点击了确认",Toast.LENGTH_LONG).show();
                        findViewById(R.id.my_text3).setEnabled(true);
                    }
                });
                alertDialog.setCancelable(false);//防止按下返回键就关闭弹框
                alertDialog.show();
            }
        });
        findViewById(R.id.my_text4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.my_text4).setEnabled(false);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(ConstraintLayout_Mine.this);

                alertDialog.setMessage("我是4，我的左边和1的右边对齐，我的上边和1的下边对齐，app:layout_constraintLeft_toRightOf=\"@+id/my_text3\"\n" +
                        "        app:layout_constraintTop_toBottomOf=\"@+id/my_text1\"\n" +
                        "        android:text=\"4\"\n" +
                        "        android:layout_width=\"wrap_content\"\n" +
                        "        android:layout_height=\"wrap_content\"");

                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(ConstraintLayout_Mine.this,"点击了确认",Toast.LENGTH_LONG).show();
                        findViewById(R.id.my_text4).setEnabled(true);
                    }
                });
                alertDialog.setCancelable(false);//防止按下返回键就关闭弹框
                alertDialog.show();
            }
        });
        findViewById(R.id.my_text5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.my_text5).setEnabled(false);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(ConstraintLayout_Mine.this);

                alertDialog.setMessage("我是5，我的左边和3的右边对齐，我的上边和3的下边对齐，app:layout_constraintLeft_toRightOf=\"@+id/my_text3\"\n" +
                        "        app:layout_constraintTop_toBottomOf=\"@+id/my_text3\"\n" +
                        "        android:text=\"5\"\n" +
                        "        android:layout_width=\"wrap_content\"\n" +
                        "        android:layout_height=\"wrap_content\"");

                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(ConstraintLayout_Mine.this,"点击了确认",Toast.LENGTH_LONG).show();
                        findViewById(R.id.my_text5).setEnabled(true);
                    }
                });
                alertDialog.setCancelable(false);//防止按下返回键就关闭弹框
                alertDialog.show();
            }
        });
        findViewById(R.id.my_text6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.my_text6).setEnabled(false);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(ConstraintLayout_Mine.this);

                alertDialog.setMessage("我是6，我的左边和4的右边对齐，我的下边和4的上边对齐，  app:layout_constraintLeft_toRightOf=\"@+id/my_text4\"\n" +
                        "        app:layout_constraintBottom_toTopOf=\"@+id/my_text4\"\n" +
                        "        android:text=\"6\"\n" +
                        "        android:layout_width=\"wrap_content\"\n" +
                        "        android:layout_height=\"wrap_content\"");

                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(ConstraintLayout_Mine.this,"点击了确认",Toast.LENGTH_LONG).show();
                        findViewById(R.id.my_text6).setEnabled(true);
                    }
                });
                alertDialog.setCancelable(false);//防止按下返回键就关闭弹框
                alertDialog.show();
            }
        });
        findViewById(R.id.my_text7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.my_text7).setEnabled(false);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(ConstraintLayout_Mine.this);
                //setTitle的话，文本会显示不全
                alertDialog.setMessage("我是7，我的上边和5的下边对齐，我的左边和5的左边对齐.我的属性是  android:layout_width=\"0dp\"\n" +
                        "        android:layout_height=\"wrap_content\"\n" +
                        "        app:layout_constraintDimensionRatio=\"1:1\"\n" +
                        "        android:id=\"@+id/my_text7\"\n" +
                        "        app:layout_constraintTop_toBottomOf=\"@+id/my_text5\"\n" +
                        "        app:layout_constraintLeft_toLeftOf=\"@+id/my_text5\"");


                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(ConstraintLayout_Mine.this,"点击了确认",Toast.LENGTH_LONG).show();
                        findViewById(R.id.my_text7).setEnabled(true);
                    }
                });
                alertDialog.setCancelable(false);//防止按下返回键就关闭弹框
                alertDialog.show();
            }
        });
        findViewById(R.id.my_text8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.my_text8).setEnabled(false);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(ConstraintLayout_Mine.this);
                //setTitle的话，文本会显示不全
                alertDialog.setMessage("我是8，我在最中间.我的属性是  app:layout_constraintBottom_toBottomOf=\"parent\"\n" +
                        "        app:layout_constraintLeft_toLeftOf=\"parent\"\n" +
                        "        app:layout_constraintRight_toRightOf=\"parent\"\n" +
                        "        app:layout_constraintTop_toTopOf=\"parent\"\n" +
                        "        android:layout_width=\"wrap_content\"\n" +
                        "        android:layout_height=\"wrap_content\"");


                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(ConstraintLayout_Mine.this,"点击了确认",Toast.LENGTH_LONG).show();
                        findViewById(R.id.my_text8).setEnabled(true);
                    }
                });
                alertDialog.setCancelable(false);//防止按下返回键就关闭弹框
                alertDialog.show();
            }
        });
        findViewById(R.id.my_text9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.my_text9).setEnabled(false);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(ConstraintLayout_Mine.this);
                //setTitle的话，文本会显示不全
                alertDialog.setMessage("我是9，我在最中间的基础上向有平移了200dp.我的属性是android:layout_marginLeft=\"200dp\"\n" +
                        "        app:layout_constraintBottom_toBottomOf=\"parent\"\n" +
                        "        app:layout_constraintLeft_toLeftOf=\"parent\"\n" +
                        "        app:layout_constraintRight_toRightOf=\"parent\"\n" +
                        "        app:layout_constraintTop_toTopOf=\"parent\"\n" +
                        "        android:layout_width=\"wrap_content\"\n" +
                        "        android:layout_height=\"wrap_content\"");


                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(ConstraintLayout_Mine.this,"点击了确认",Toast.LENGTH_LONG).show();
                        findViewById(R.id.my_text9).setEnabled(true);
                    }
                });
                alertDialog.setCancelable(false);//防止按下返回键就关闭弹框
                alertDialog.show();
            }
        });


        findViewById(R.id.my_text10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findViewById(R.id.group).getVisibility()==View.GONE){
                    findViewById(R.id.group).setVisibility(View.VISIBLE);
                }else{
                    findViewById(R.id.group).setVisibility(View.GONE);
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
//                startActivity(new Intent(SelfActivity.this, MainActivity.class));
               //跳转网页教程
                startActivity(new Intent(ConstraintLayout_Mine.this,HrefWebView.class).putExtra("url",
                        "https://blog.csdn.net/xianKOG/article/" +
                                "details/114258431?ops_request_" +
                                "misc=%257B%2522request%255Fid%25" +
                                "22%253A%252216528426991678166786664" +
                                "2%2522%252C%2522scm%2522%253A%25222014" +
                                "0713.130102334.pc%255Fall.%2522%257D&reque" +
                                "st_id=165284269916781667866642&biz_id=0&u" +
                                "tm_medium=distribute.pc_search_result.none-ta" +
                                "sk-blog-2~all~first_rank_ecpm_v1~rank_v31_ec" +
                                "pm-1-114258431-null-null.142%5Ev10%5Econtrol,15" +
                                "7%5Ev4%5Econtrol&utm_term=%E9%99%90%E5%88%B6%E6%80" +
                                "%A7%E5%B8%83%E5%B1%80ContraintLayout&spm=1018.2226" +
                                ".3001.4187").putExtra("title","ConstraintLayout(限制性/约束性布局)"));

                return false;

            }
        });
        // 相当于在res/menu/main.xml文件中，给menu增加一个新的条目item，这个条目会显示title标签的文字(如备注1)

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }

}