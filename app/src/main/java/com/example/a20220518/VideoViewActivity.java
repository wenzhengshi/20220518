package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.a20220518.adapter.GridViewAdapter;

public class VideoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("VideoView");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        VideoView videoView=findViewById(R.id.video_view);
        /**播放 res/raw 目录下的文件
         * android.resource:// ：前缀固定
         * com.example.administrator.helloworld：为当前类的所在的包路径，可以使用 String packageName = getPackageName(); 动态获取
         * R.raw.la_isla：最后接 res/raw 目录中的文件名
         * */

        videoView.setVideoURI(Uri.parse("android.resource://com.example.a20220518/"+R.raw.movie));

        /**
         * 为 VideoView 视图设置媒体控制器，设置了之后就会自动由进度条、前进、后退等操作
         */
        videoView.setMediaController(new MediaController(this));

        /**
         * 视频播放完成时回调
         */
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i("tag", "------------------视频播放完毕..........");
                /**播放完成时，再次循环播放*/
                videoView.start();
            }
        });
        /**
         * 视频播放发送错误时回调
         */
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i("tag", "---------------------视频播放失败...........");
                return false;
            }
        });

        /**开始播放视频
         * */
        videoView.start();



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
                startActivity(new Intent(VideoViewActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/Cyril_KI/article/d" +
                        "etails/108422321?ops_request_misc=%257B%2522requ" +
                        "est%255Fid%2522%253A%2522165294754816781432944099" +
                        "%2522%252C%2522scm%2522%253A%252220140713.130102" +
                        "334..%2522%257D&request_id=165294754816781432944" +
                        "099&biz_id=0&utm_medium=distribute.pc_search_resu" +
                        "lt.none-task-blog-2~all~top_posit" +
                        "ive~default-1-108422321-null-null.142^v10^contr" +
                        "ol,157^v4^control&utm_term=GridView&spm=1018.2" +
                        "226.3001.4187").putExtra("title","VideoView详解"));
                return false;

            }
        });


        menu.add(0, 1, 2, "在线的VideoView").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//
                startActivity(new Intent(VideoViewActivity.this,VideoViewOnlineActivity.class));
                return false;

            }
        });



        menu.add(0, 2, 3, "取消");

        return super.onCreateOptionsMenu(menu);

    }
}
