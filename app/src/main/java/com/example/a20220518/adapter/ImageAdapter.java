package com.example.a20220518.adapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.example.a20220518.R;
import com.example.a20220518.img_view_3d.MyGallery;

public class ImageAdapter extends BaseAdapter {
    private ImageView[] mImages; // 存储每个图片的ImageView

    private Context mContext;
    public List<Map<String, Object>> list;

    public Integer[] imgs = { R.drawable.img1, R.drawable.img2,
            R.drawable.img3, R.drawable.img4, R.drawable.img5 };
    public String[] titles = { "1", "2", "3", "4", "5"};

    public ImageAdapter(Context c) {
        this.mContext = c;
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imgs.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("image", imgs[i]);
            list.add(map);
        }
        mImages = new ImageView[list.size()];
    }

    /**
     * 创建倒影效果
     */
    @SuppressWarnings("deprecation")
    public boolean createReflectedImages() {
        final int reflectionGap = 4;//原图与倒影之间的间隙
        int index = 0;
        for (Map<String, Object> map : list) {
            Integer id = (Integer) map.get("image");
            Bitmap originalImage = BitmapFactory.decodeResource(
                    mContext.getResources(), id); // 获得图片资源
            // 获得图片的长宽
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            Matrix matrix = new Matrix();
            matrix.preScale(1, -1); // 实现图片的反转
            Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
                    height / 2, width, height / 2, matrix, false); // 创建反转后的图片Bitmap对象，图片高是原图的一半
            Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
                    (height + height / 2), Config.ARGB_8888); // 创建标准的Bitmap对象，宽和原图一致，高是原图的1.5倍

            Canvas canvas = new Canvas(bitmapWithReflection);
            canvas.drawBitmap(originalImage, 0, 0, null); // 创建画布对象，将原图画于画布，起点是原点位置
            Paint paint = new Paint();
            canvas.drawRect(0, height, width, height + reflectionGap, paint);
            canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null); // 将反转后的图片画到画布中

            paint = new Paint();
            LinearGradient shader = new LinearGradient(0,
                    originalImage.getHeight(), 0,
                    bitmapWithReflection.getHeight() + reflectionGap,
                    0x70ffffff, 0x00ffffff, TileMode.MIRROR);// 创建线性渐变LinearGradient对象
            paint.setShader(shader); // 绘制
            paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));//倒影遮罩效果
            canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
                    + reflectionGap, paint); // 画布画出反转图片大小区域，然后把渐变效果加到其中，就出现了图片的倒影效果

            ImageView imageView = new ImageView(mContext);
            imageView.setImageBitmap(bitmapWithReflection); // 设置带倒影的Bitmap
            //设置ImageView的大小，可以根据图片大小设置
            // imageView.setLayoutParams(newmyGallery.LayoutParams(width,height));
            imageView.setLayoutParams(new MyGallery.LayoutParams(250, 500));//设置ImageView的大小，可根据需要设置固定宽高
            imageView.setScaleType(ScaleType.FIT_CENTER);//将图片按比例缩放
            mImages[index++] = imageView;
        }
        return true;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return mImages[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mImages[position]; // 获得Gallery中对应位置的ImageView
    }

    public float getScale(boolean focused, int offset) {
        return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
    }

}

