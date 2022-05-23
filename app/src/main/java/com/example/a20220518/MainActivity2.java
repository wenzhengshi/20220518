package com.example.a20220518;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;

public class MainActivity2 extends AppCompatActivity {


    private Bitmap data_img;

    public static final String MIME_TYPE_IMAGE_JPEG = "image/*";
    public static final int ACTIVITY_GET_IMAGE = 0;
    private Uri uri;
    private Bitmap bitmap_save_local;//用来保存到本地
    private int CAMERA_REQ_CODE = 1;//调用相机
    private ImageView my_img;
    private int my_resultCode;
    private Intent self_intent;

    ActivityResultLauncher launcher = registerForActivityResult(new ResultContract(), new ActivityResultCallback<String>() {
        @Override
        public void onActivityResult(String result) {
            if (my_resultCode == RESULT_OK) {
                if (self_intent != null && self_intent.hasExtra("data")) {
                    // 方法一：没有指定 uri 时图片资源由 data.getParcelableExtra("data") 获取
                    System.out.println("崩溃起点？？？？");
                    bitmap_save_local=self_intent.getParcelableExtra("data");
                    my_img.setImageBitmap(self_intent.getParcelableExtra("data"));
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
            } else if (my_resultCode == RESULT_CANCELED) {
                System.out.println("拍照取消");
            }
            Toast.makeText(MainActivity2.this, result, Toast.LENGTH_SHORT).show();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("startActivityForResult的替代方法");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        my_img=findViewById(R.id.my_img);
        launcher.launch(true);
    }

    class ResultContract extends ActivityResultContract<Boolean, String> {
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Boolean input) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            String path = getFilesDir().getAbsolutePath() + File.separator + "test.jpg"; //这里只是当前目录，如果是多级文件夹 得通过 mkdirs 创建
            File file = new File(path);

            //由于 Android 文件安全机制 向第三方应用提供路径的时候得使用 FileProvider，注意需要在清单文件中注册
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                uri = FileProvider.getUriForFile(MainActivity2.this, "com.wen.guo.qiang", file);
            } else {
                uri = Uri.fromFile(file);
            }

            //这里设置了 uri 那么后面就不能使用 data.getParcelableExtra("data") 获取图片了
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

//            startActivityForResult(intent, CAMERA_REQ_CODE);
            new Intent(MainActivity2.this, intent.getClass());

            return intent;
        }





        @Override
        public String parseResult(int resultCode, @Nullable Intent data) {

            if(resultCode==RESULT_OK){
                my_resultCode=RESULT_OK;
            }else{
                my_resultCode=RESULT_CANCELED;
            }
            self_intent=data;


            return "保存成功？";
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
}

