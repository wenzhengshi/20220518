package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {
    private  Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alert_dialog);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("对话框AlertDialog");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮
        }

        btn1=findViewById(R.id.dialog_1);
        btn2=findViewById(R.id.dialog_2);
        btn3=findViewById(R.id.dialog_3);
        btn4=findViewById(R.id.dialog_4);
        btn5=findViewById(R.id.dialog_5);
        btn6=findViewById(R.id.dialog_6);
        btn7=findViewById(R.id.dialog_7);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_1:
                showAlterDialog();
                break;
            case R.id.dialog_2:

                showListDialog();
                break;
            case R.id.dialog_3:

                showMultiChoiceDialog();
                break;
            case R.id.dialog_4:

                showProgressDialog();
                break;
            case R.id.dialog_5:

                showWhiteDialog();
                break;
            case R.id.dialog_6:

                diyDialog1();
                break;
            case R.id.dialog_7:

                diyDialog2();
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
//                startActivity(new Intent(SelfActivity.this, MainActivity.class));
                //跳转网页教程
                startActivity(new Intent(AlertDialogActivity.this,HrefWebView.class).putExtra("url",
                        " https://blog.csdn.net/sakurakider/article/details/80735400?ops_request_misc=%257" +
                                "B%2522request%255Fid%2522%253A%2522165312314616781435441845%2522%252C%2522sc" +
                                "m%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=1653123" +
                                "14616781435441845&biz_id=0&utm_medium=distribute.pc_search_result.none-ta" +
                                "sk-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-2-80735400-null-null.142^v10^" +
                                "control,157^v4^control&utm_term=dialog&spm=1018.2226." +
                                "3001.4187").putExtra("title","AlertDialog"));

                return false;

            }
        });
        // 相当于在res/menu/main.xml文件中，给menu增加一个新的条目item，这个条目会显示title标签的文字(如备注1)

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }




    /**
     * 普通dialog
     */
    private void showAlterDialog(){
        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(AlertDialogActivity.this);
        alterDiaglog.setIcon(R.drawable.ic_launcher_background);//图标
        alterDiaglog.setTitle("简单的dialog");//文字
        alterDiaglog.setMessage("生存还是死亡");//提示消息
        //积极的选择
        alterDiaglog.setPositiveButton("生存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this,"点击了生存",Toast.LENGTH_SHORT).show();
            }
        });
        //消极的选择
        alterDiaglog.setNegativeButton("死亡", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this,"点击了死亡",Toast.LENGTH_SHORT).show();
            }
        });
        //中立的选择
        alterDiaglog.setNeutralButton("不生不死", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this,"点击了不生不死",Toast.LENGTH_SHORT).show();
            }
        });

        //显示
        alterDiaglog.show();
    }




    /**
     * 列表Dialog
     */
    private void showListDialog(){
        final String[] items = {"我是1","我是2","我是3"};
        AlertDialog.Builder listDialog = new AlertDialog.Builder(AlertDialogActivity.this);
        listDialog.setIcon(R.drawable.ic_launcher_background);//图标
        listDialog.setTitle("我就是个列表Dialog");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this,"点击了"+items[which],Toast.LENGTH_SHORT).show();
            }
        });
        listDialog.show();
    }



    /**
     * 多选对话框
     */
    ArrayList<Integer> choices= new ArrayList<>();
    private void showMultiChoiceDialog(){
        final String[] items = {"我是1","我是2","我是3"};
        //设置默认选择都是false
        final boolean initchoices[] = {false,false,false};
        choices.clear();
        AlertDialog.Builder multChoiceDialog = new AlertDialog.Builder(AlertDialogActivity.this);
        multChoiceDialog.setIcon(R.drawable.ic_launcher_background);
        multChoiceDialog.setTitle("我是个多选Dialog");
        multChoiceDialog.setMultiChoiceItems(items, initchoices, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked){
                    choices.add(which);
                }else {
                    choices.remove(which);
                }
            }
        });
        multChoiceDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int size = choices.size();
                String str = "";
                for(int i = 0;i<size;i++){
                    str+=items[choices.get(i)]+"";
                }
                Toast.makeText(AlertDialogActivity.this,
                        "你选中了" + str,
                        Toast.LENGTH_SHORT).show();
            }
        });
        multChoiceDialog.show();
    }



    private void showProgressDialog(){
        final int MAX = 100;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("我是个等待的Dialog");
        progressDialog.setMessage("等待中");
        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
        progressDialog.show();
    }



    /**
     * 进度条Dialog
     */
    private void showWhiteDialog(){
        /* @setProgress 设置初始进度
         * @setProgressStyle 设置样式（水平进度条）
         * @setMax 设置进度最大值
         */
        final int Max = 100;
        final ProgressDialog progressDialog = new ProgressDialog(AlertDialogActivity.this);
        progressDialog.setProgress(0);
        progressDialog.setIcon(R.drawable.ic_launcher_background);
        progressDialog.setTitle("我是一个进度条Dialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(Max);
        progressDialog.show();
        /**
         * 开个线程
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                int p = 0;
                while (p<Max){
                    try {
                        Thread.sleep(100);
                        p++;
                        progressDialog.setProgress(p);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                progressDialog.cancel();//达到最大就消失
            }

        }).start();
    }



    /**
     * 自定义1 控制普通的dialog的位置，大小，透明度
     * 在普通的dialog.show下面添加东西
     */
    private void diyDialog1(){
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(AlertDialogActivity.this);
        alterDiaglog.setIcon(R.drawable.ic_launcher_background);//图标
        alterDiaglog.setTitle("简单的dialog");//文字
        alterDiaglog.setMessage("生存还是死亡");//提示消息
        //积极的选择
        alterDiaglog.setPositiveButton("生存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this,"点击了生存",Toast.LENGTH_SHORT).show();
            }
        });
        //消极的选择
        alterDiaglog.setNegativeButton("死亡", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this,"点击了死亡",Toast.LENGTH_SHORT).show();
            }
        });

        alterDiaglog.setNeutralButton("不生不死", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this,"点击了不生不死",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alterDiaglog.create();

        //显示
        dialog.show();
        //自定义的东西
        //放在show()之后，不然有些属性是没有效果的，比如height和width
        Window dialogWindow = dialog.getWindow();
        WindowManager m = getWindowManager();

        DisplayMetrics d = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(d);
        int screen_wight  = d.widthPixels;
        int screen_height = d.heightPixels;

//        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // 设置高度和宽度
        p.height = (int) (screen_wight * 0.4); // 高度设置为屏幕的0.6
        p.width = (int) (screen_height * 0.6); // 宽度设置为屏幕的0.65

        p.gravity = Gravity.TOP;//设置位置

        p.alpha = 0.8f;//设置透明度
        dialogWindow.setAttributes(p);
    }

    /**
     * 自定义dialog2 简单自定义布局
     */
    private void diyDialog2() {
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(AlertDialogActivity.this,R.style.MyDialog);
        alterDiaglog.setView(R.layout.dialog_1);//加载进去
        AlertDialog dialog = alterDiaglog.create();
        //显示
        dialog.show();
        //自定义的东西
    }
}