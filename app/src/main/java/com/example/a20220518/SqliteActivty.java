package com.example.a20220518;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.a20220518.sqlite_helper.MyOpenHelper;

public class SqliteActivty extends AppCompatActivity {
    MyOpenHelper myOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_activty);

        myOpenHelper=new MyOpenHelper(getApplicationContext());
        //打开或者创建数据库  如果是第一次就是创建
        //SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();

        //打开或创建数据库 如果是第一次就是创建  如果磁盘满了只返回一个可读的
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getReadableDatabase();


    }

    //点击按钮增加一条记录
    public void click1(View view) {
        //[1]获取数据库对象
        SQLiteDatabase db=myOpenHelper.getReadableDatabase();
        //[2]执行增加一条的sql语句
        db.execSQL("insert into info(name,phone) values(?,?)",new Object[]{"张三","138888"});
        //[3]数据库用完需要关闭
        db.close();
    }


    //删除
    public void click2(View view) {

        SQLiteDatabase db=myOpenHelper.getReadableDatabase();

        db.execSQL("delete from info where name=?",new Object[]{"张三"});

        db.close();
    }

    //更新
    public void click3(View view) {

        SQLiteDatabase db=myOpenHelper.getReadableDatabase();

        db.execSQL("update info set phone=? where name=?",new Object[]{"139999"});

        db.close();

    }

    //查找
    public void click4(View view) {

        SQLiteDatabase db=myOpenHelper.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from info",null);
        if (cursor!=null&&cursor.getCount()>0){
            while (cursor.moveToNext()){
                //columindex代表列的索引
                String name=cursor.getString(1);
                String phone=cursor.getString(2);
                System.out.println("name:"+name+"---------"+phone);
            }
        }
    }
}