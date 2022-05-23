package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Contacts;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.a20220518.adapter.GridViewAdapter;
import com.example.a20220518.adapter.GridViewImgAdapter;
import com.example.a20220518.query_img.QueryImageUtil;
import com.example.a20220518.uri_util.UriUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/plate";
//    private  String filePath ;
    private static final String TAG = "Main2Activity";
    private ArrayList<Bitmap> imageList = new ArrayList<>();
    private EditText researchEdit;
    private Button researchBtn;
    private ListView listview;
    private String editContent;

    //异步加载图片

     private Handler get_img_data;
     private Bitmap data_img;

    public static final String MIME_TYPE_IMAGE_JPEG = "image/*";
    public static final int ACTIVITY_GET_IMAGE = 0;


    private int CAMERA_REQ_CODE = 1;//调用相机
    private ImageView my_img;

    private VideoView videoView;
    private Uri uri;

    private Bitmap bitmap_save_local;//用来保存到本地




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("调用相机拍照并显示");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        my_img=findViewById(R.id.my_img);
        videoView=findViewById(R.id.video_view);
        //先动态请求相机权限
        if(checkPermission()) {
            //跳转时未设置 uri
           camera(this.getWindow().getDecorView());
        }

        //拨打电话
//        Uri uri = Uri.parse("tel:13105050392");
//        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
//        startActivity(intent);


//        //查看指定联系人
//        Uri personUri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, 1);//info.id联系人ID
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(personUri);
//        startActivity(intent);

//        //进入联系人页面
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(Contacts.People.CONTENT_URI);
//        startActivity(intent);


//        //搜索应用（此功能可用于让用户更新app）
//        Uri uri = Uri.parse("market://search?q=pname:java修炼手册");
//        Intent it = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(it);



////发送附件（这个用不了）
//        Intent it = new Intent(Intent.ACTION_SEND);
//        it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
//        it.putExtra(Intent.EXTRA_STREAM, "[url=]file:///sdcard/eoe.mp3[/url]");
//        startActivity(Intent.createChooser(it, "Choose Email Client"));


//        //调用相册
//
//        Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
//        getImage.addCategory(Intent.CATEGORY_OPENABLE);
//        getImage.setType(MIME_TYPE_IMAGE_JPEG);
//        startActivityForResult(getImage, ACTIVITY_GET_IMAGE);


//        //调用系统相机应用程序，并存储拍下来的照片
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        long time = Calendar.getInstance().getTimeInMillis();
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
//                .getExternalStorageDirectory().getAbsolutePath()+"/tucue", time + ".jpg")));
//        startActivityForResult(intent, );

        /**未测试:卸载程序**/
//        Uri packageURI = Uri.parse("package:"+"com.example.a20220518");
//        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
//        startActivity(uninstallIntent);



        System.out.println("filePath文件路径："+filePath);



    }




    public void camera(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String path = getFilesDir().getAbsolutePath() + File.separator + "test.jpg"; //这里只是当前目录，如果是多级文件夹 得通过 mkdirs 创建
        File file = new File(path);

        //由于 Android 文件安全机制 向第三方应用提供路径的时候得使用 FileProvider，注意需要在清单文件中注册
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            uri = FileProvider.getUriForFile(ListViewActivity.this, "com.wen.guo.qiang", file);
        } else {
            uri = Uri.fromFile(file);
        }

        //这里设置了 uri 那么后面就不能使用 data.getParcelableExtra("data") 获取图片了
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(intent, CAMERA_REQ_CODE);
    }





    //拍照回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQ_CODE && resultCode == RESULT_OK) {
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











    /**
     * 拍照权限申请
     */
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            ,Manifest.permission.WRITE_EXTERNAL_STORAGE
            String[] permissions = new String[]{Manifest.permission.CAMERA};
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, permissions, 100);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 保存本地权限申请
     */
    private boolean checkPermission2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            ,Manifest.permission.WRITE_EXTERNAL_STORAGE
            String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, permissions, 100);
                    return false;
                }
            }
        }else{

            return true;
        }
        return true;
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
                startActivity(new Intent(ListViewActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/qq_41885673/article/details/120924647").putExtra("title","调用相机拍照并显示"));
                return false;

            }
        });

        menu.add(0, 1, 2, "保存至本地").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                my_img=findViewById(R.id.my_img);
                data_img=((BitmapDrawable)my_img.getDrawable()).getBitmap();
                System.out.println(my_img);
               saveImageToGallery(ListViewActivity.this, data_img);
                return false;

            }
        });

        menu.add(0, 2, 3, "拍摄视频（未完成）").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //跳转拍摄视频
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                // 录制视频最大时长15s
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 15);
                startActivityForResult(intent, 300);

                return false;
            }
        });

        menu.add(0, 3, 4, " startActivityForResult的替代方法").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                startActivity(new Intent(ListViewActivity.this,MainActivity2.class));

                return false;
            }
        });

        menu.add(0, 4, 5, "取消");

        return super.onCreateOptionsMenu(menu);

    }




     private void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        // 其次把文件插入到系统图库
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    file.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getPath())));
         Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();

    }

     private void saveImageToGallerys(Context context, Bitmap bmp) {
        if (bmp == null){
            Toast.makeText(context, "保存出错了...", Toast.LENGTH_SHORT).show();
            return;
        }
        // 首先保存图片
//        File appDir = new File(BaseApplication.app.getTmpDir(), "ywq");
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            Toast.makeText(context, "文件未发现...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(context, "保存出错了...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }catch (Exception e){
            Toast.makeText(context, "保存出错了...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        // 最后通知图库更新
        try {

            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        Toast.makeText(context, "保存出错了...", Toast.LENGTH_SHORT).show();
    }


}
