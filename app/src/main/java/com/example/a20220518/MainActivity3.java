package com.example.a20220518;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity3 extends AppCompatActivity {

    private Bitmap data_img;

    public static final String MIME_TYPE_IMAGE_JPEG = "image/*";
    public static final int ACTIVITY_GET_IMAGE = 0;
    private Uri uri;
    private Bitmap bitmap_save_local;//用来保存到本地
    private int CAMERA_REQ_CODE = 1;//调用相机
    private ImageView my_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        my_img=findViewById(R.id.my_img);

    }

    //拍照回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("data")) {
                // 方法一：没有指定 uri 时图片资源由 data.getParcelableExtra("data") 获取
                System.out.println("崩溃起点？？？？");
                bitmap_save_local=data.getParcelableExtra("data");
                my_img.setImageBitmap(data.getParcelableExtra("data"));
                my_img.setVisibility(View.VISIBLE);
            }else{
                try {
                    InputStream is = getContentResolver().openInputStream(uri);
                    my_img.setImageBitmap(BitmapFactory.decodeStream(is));
                    my_img.setVisibility(View.VISIBLE);
                    is.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("uri 方式获取 =》 " + uri);
                System.out.println("拍照完成");
            }
        } else if (requestCode == CAMERA_REQ_CODE && resultCode == RESULT_CANCELED) {
            System.out.println("拍照取消");
        }



    }

}

