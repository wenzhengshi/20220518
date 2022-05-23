package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.DrawableUtils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class ImageViewActivity extends AppCompatActivity {

    private static final Xfermode[] sModes = {
            new PorterDuffXfermode(PorterDuff.Mode.CLEAR),     //所绘制不会提交到画布上
            new PorterDuffXfermode(PorterDuff.Mode.SRC),       //显示上层绘制图片
            new PorterDuffXfermode(PorterDuff.Mode.DST),      //显示下层绘制图片
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER), //正常绘制显示，上下层绘制叠盖
            new PorterDuffXfermode(PorterDuff.Mode.DST_OVER), //上下层都显示。下层居上显示
            new PorterDuffXfermode(PorterDuff.Mode.SRC_IN),   //取两层绘制交集。显示上层
            new PorterDuffXfermode(PorterDuff.Mode.DST_IN),   //取两层绘制交集。显示下层
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT),  //取上层绘制非交集部分
            new PorterDuffXfermode(PorterDuff.Mode.DST_OUT),  //取下层绘制非交集部分
            new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP), //取下层非交集部分与上层交集部分
            new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP), //取上层非交集部分与下层交集部分
            new PorterDuffXfermode(PorterDuff.Mode.XOR),      //滤色效果
            new PorterDuffXfermode(PorterDuff.Mode.DARKEN),   //滤色效果
            new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN),  //滤色效果
            new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY), //滤色效果
            new PorterDuffXfermode(PorterDuff.Mode.SCREEN) };   //滤色效果

    private ImageView mRevertImageView;
    private Bitmap mSourceBitmap;  //原图
    private Bitmap mRevertBitmap;  //倒立图

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("ImageView");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }


        mRevertImageView = findViewById(R.id.img_4);
        mSourceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mountain);
        mRevertImageView.setBackground(new BitmapDrawable(getResources(), revertBitmap()));




    }




    private Bitmap revertBitmap() {
        //1.倒立图
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);   //以X轴向下翻转
        int width = mSourceBitmap.getWidth();
        int height = mSourceBitmap.getHeight();

        //生成倒立图，宽度和原图一致，高度为原图的一半
        mRevertBitmap = Bitmap.createBitmap(mSourceBitmap, 0, height / 2, width, height / 2, matrix, false);

        //2.要生成原图加上倒立图，先生成一个可变空的Bitmap, 高度为原图高度的1.5倍（包括原图和倒立图的高度）
        int gap = 10; //间隙空白
        Bitmap bitmap = Bitmap.createBitmap(width, height + height / 2, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(mSourceBitmap, 0, 0, paint);  //绘制原图
        canvas.drawBitmap(mRevertBitmap, 0, height + gap, paint);  //绘制倒立图

        //3.画笔使用LinearGradient 线性渐变渲染
        LinearGradient lg = new LinearGradient(0, height + gap, width, bitmap.getHeight(), 0xabff0000, 0x00ffff00, Shader.TileMode.MIRROR);
        paint.setShader(lg);

        //4.指定画笔的Xfermode 即绘制的模式（不同的模式，绘制的区域不同）
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));

        //5.在倒立图区，绘制矩形渲染图层
        canvas.drawRect(0, height + gap, width, bitmap.getHeight(), paint);
        paint.setXfermode(null);
        return bitmap;
    }

    //缩放图片
    private Bitmap resizeImage(Bitmap bitmap, int width, int height) {
        int originWidth = bitmap.getWidth();
        int originHeight = bitmap.getHeight();

        float scaleWidth = width / originWidth;
        float scaleHeight = height / originHeight;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0, originWidth, originHeight, matrix, true);
        return resizeBitmap;
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
                startActivity(new Intent(ImageViewActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/shineflowers/art" +
                        "icle/details/47172253?ops_request_misc=%2" +
                        "57B%2522request%255Fid%2522%253A%2522165305" +
                        "368116782390597653%2522%252C%2522scm%2522%25" +
                        "3A%252220140713.130102334.pc%255Fall.%2522%2" +
                        "57D&request_id=165305368116782390597653&biz_" +
                        "id=0&utm_medium=distribute.pc_search_result." +
                        "none-task-blo" +
                        "g-2~all~first_rank_ecpm_v1~rank_v31_ecpm-20-" +
                        "47172253-null-null.142^v10^control,157^v4^contro" +
                        "l&utm_term=ImageView倒影&spm=1018.2226.3001.4187").putExtra("title","ImageView倒影"));
                return false;

            }
        });



        menu.add(0, 1, 2, "3D效果").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//
                startActivity(new Intent(ImageViewActivity.this,ImageView2Activity.class));
                return false;

            }
        });



        menu.add(0, 2, 3, "取消");

        return super.onCreateOptionsMenu(menu);

    }

}