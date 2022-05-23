package com.example.a20220518.broad_cast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Shader;
import android.util.Log;
import android.widget.Toast;

import com.example.a20220518.save.SharedP;
import com.example.a20220518.time.GetNowTime;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();//获取到收到的广播的名称
        Log.e("hui", "收到的广播的Action是："+action);

        //接收到广播后将本次时间存入数据
        SharedP sp=new SharedP(context,"20220518");
        sp.putValue("broad_cast_sate", GetNowTime.getNowTime());


    }
}

