package com.example.a20220518;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class SubMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);


    }

    /*
     *设置menu子菜单显示的内容
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);

        //通过addSubMenu方法添加两个子菜单
        SubMenu file=menu.addSubMenu("文件");
        SubMenu edit=menu.addSubMenu("编辑");
        //设置file子菜单中的内容（设置GroupId为1）
        file.add(1, 1, 1, "查看教程");
        file.add(1, 2, 1, "打开");
        file.add(1, 3, 1, "保存");
        file.setHeaderTitle("文件操作");  //设置子菜单的标题
        file.setHeaderIcon(R.drawable.ic_launcher_background);  //设置标题旁的图片
        //设置edit子菜单中的内容（设置GroupId为2）
        edit.add(2, 1, 1, "复制");
        edit.add(2, 2, 1, "粘贴");
        edit.add(2, 3, 1, "剪切");
        edit.setHeaderTitle("编辑操作");
        edit.setHeaderIcon(R.drawable.ic_launcher_background);
        return true;
    }
    /*
     * 设置菜单项的点击事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 通过GroupId来判断是哪一个子菜单
        if (item.getGroupId()==1) {

            //通过ItemId来判断选中的是子菜单中的哪一项
            switch (item.getItemId()) {
                case 1:

                    startActivity(new Intent(SubMenuActivity.this,HrefWebView.class).putExtra("url","" +
                            "https://blog.csdn.net/ljw124213/article/details/4966590" +
                            "9?spm=1001.2101.3001.6650.17&utm_medium=distribute.pc_rele" +
                            "vant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Ede" +
                            "fault-17-49665909-blog-43306123.pc_relevant_default&depth_1-" +
                            "utm_source=distribute.pc" +
                            "_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBai" +
                            "du%7Edefault-17-49665909-blog-43306123.pc_relev" +
                            "ant_default&utm_relevant_index=20").putExtra("title","SubMenu"));

                    break;
                case 2:
                    Toast.makeText(this, "点击了打开", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(this, "点击了保存", Toast.LENGTH_SHORT).show();
                    break;
            }
        }else if(item.getGroupId()==2){
            switch (item.getItemId()) {
                case 1:
                    Toast.makeText(this, "点击了复制", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(this, "点击了粘贴", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(this, "点击了剪切", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}