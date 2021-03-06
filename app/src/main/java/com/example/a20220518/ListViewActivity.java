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

    //??????????????????

     private Handler get_img_data;
     private Bitmap data_img;

    public static final String MIME_TYPE_IMAGE_JPEG = "image/*";
    public static final int ACTIVITY_GET_IMAGE = 0;


    private int CAMERA_REQ_CODE = 1;//????????????
    private ImageView my_img;

    private VideoView videoView;
    private Uri uri;

    private Bitmap bitmap_save_local;//?????????????????????




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("???????????????????????????");
            ac.setDisplayHomeAsUpEnabled(true);  // ??????????????????

        }
        my_img=findViewById(R.id.my_img);
        videoView=findViewById(R.id.video_view);
        //???????????????????????????
        if(checkPermission()) {
            //?????????????????? uri
           camera(this.getWindow().getDecorView());
        }

        //????????????
//        Uri uri = Uri.parse("tel:13105050392");
//        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
//        startActivity(intent);


//        //?????????????????????
//        Uri personUri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, 1);//info.id?????????ID
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(personUri);
//        startActivity(intent);

//        //?????????????????????
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(Contacts.People.CONTENT_URI);
//        startActivity(intent);


//        //????????????????????????????????????????????????app???
//        Uri uri = Uri.parse("market://search?q=pname:java????????????");
//        Intent it = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(it);



////?????????????????????????????????
//        Intent it = new Intent(Intent.ACTION_SEND);
//        it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
//        it.putExtra(Intent.EXTRA_STREAM, "[url=]file:///sdcard/eoe.mp3[/url]");
//        startActivity(Intent.createChooser(it, "Choose Email Client"));


//        //????????????
//
//        Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
//        getImage.addCategory(Intent.CATEGORY_OPENABLE);
//        getImage.setType(MIME_TYPE_IMAGE_JPEG);
//        startActivityForResult(getImage, ACTIVITY_GET_IMAGE);


//        //????????????????????????????????????????????????????????????
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        long time = Calendar.getInstance().getTimeInMillis();
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
//                .getExternalStorageDirectory().getAbsolutePath()+"/tucue", time + ".jpg")));
//        startActivityForResult(intent, );

        /**?????????:????????????**/
//        Uri packageURI = Uri.parse("package:"+"com.example.a20220518");
//        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
//        startActivity(uninstallIntent);



        System.out.println("filePath???????????????"+filePath);



    }




    public void camera(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String path = getFilesDir().getAbsolutePath() + File.separator + "test.jpg"; //??????????????????????????????????????????????????? ????????? mkdirs ??????
        File file = new File(path);

        //?????? Android ?????????????????? ???????????????????????????????????????????????? FileProvider???????????????????????????????????????
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            uri = FileProvider.getUriForFile(ListViewActivity.this, "com.wen.guo.qiang", file);
        } else {
            uri = Uri.fromFile(file);
        }

        //??????????????? uri ??????????????????????????? data.getParcelableExtra("data") ???????????????
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(intent, CAMERA_REQ_CODE);
    }





    //????????????
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQ_CODE && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("data")) {
                // ???????????????????????? uri ?????????????????? data.getParcelableExtra("data") ??????
                System.out.println("????????????????????????");
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
                System.out.println("uri ???????????? =??? " + uri);
                System.out.println("????????????");
            }
        } else if (requestCode == CAMERA_REQ_CODE && resultCode == RESULT_CANCELED) {
            System.out.println("????????????");
        }



    }











    /**
     * ??????????????????
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
     * ????????????????????????
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





    // ????????????????????????????????????????????????????????????Activity
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

        menu.add(0, 0, 1, "????????????").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//
                startActivity(new Intent(ListViewActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/qq_41885673/article/details/120924647").putExtra("title","???????????????????????????"));
                return false;

            }
        });

        menu.add(0, 1, 2, "???????????????").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                my_img=findViewById(R.id.my_img);
                data_img=((BitmapDrawable)my_img.getDrawable()).getBitmap();
                System.out.println(my_img);
               saveImageToGallery(ListViewActivity.this, data_img);
                return false;

            }
        });

        menu.add(0, 2, 3, "???????????????????????????").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //??????????????????
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                // ????????????????????????15s
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 15);
                startActivityForResult(intent, 300);

                return false;
            }
        });

        menu.add(0, 3, 4, " startActivityForResult???????????????").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                startActivity(new Intent(ListViewActivity.this,MainActivity2.class));

                return false;
            }
        });

        menu.add(0, 4, 5, "??????");

        return super.onCreateOptionsMenu(menu);

    }




     private void saveImageToGallery(Context context, Bitmap bmp) {
        // ??????????????????
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

//        // ????????????????????????????????????
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    file.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        // ????????????????????????
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getPath())));
         Toast.makeText(context, "????????????", Toast.LENGTH_SHORT).show();

    }

     private void saveImageToGallerys(Context context, Bitmap bmp) {
        if (bmp == null){
            Toast.makeText(context, "???????????????...", Toast.LENGTH_SHORT).show();
            return;
        }
        // ??????????????????
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
            Toast.makeText(context, "???????????????...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(context, "???????????????...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }catch (Exception e){
            Toast.makeText(context, "???????????????...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        // ????????????????????????
        try {

            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        Toast.makeText(context, "???????????????...", Toast.LENGTH_SHORT).show();
    }


}
