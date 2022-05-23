package com.example.a20220518.sqlite_helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyOpenHelper extends SQLiteOpenHelper {
    /**
     *
     *  context 上下文
     *   name  数据库的名字
     *  factory   目的创建cursor对象
     *  version    数据库的版本  从1开始
     */
    public MyOpenHelper(Context context) {
        super(context, "itheima.db", null, 4);
    }
    /**
     * 当数据库第一次创建的时候调用
     * 那么这个方法特别适合做表结构的初始化 创建表就是写sql语句
     *
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //id 一般以_id
        db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20),phone varchar(20))");
    }
    /**
     *
     * 当数据库版本升级的时候调用
     *
     * 这个方法适合做 表结构的更新
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        System.out.println("升级");
        db.execSQL("alter table info add phone varchar(20)");
    }
}


