package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewOnlineActivity extends AppCompatActivity {
    private VideoView videoView_online;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_online);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("在线VideoView");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        videoView_online=findViewById(R.id.video_view_online);
        bindViews();
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


    private void bindViews() {

        String url = "https://www.runoob.com/try/demo_source/movie.mp4";
        /**
         * 设置播放的资源路径，使用 setVideoURI(Uri uri)  或者 setVideoPath(String path)(底层也是调用的  serVideoURL(Uri uri))
         */
        videoView_online.setVideoPath(url);

        /**为控件设置焦点*/
        videoView_online.requestFocus();

        /**
         * 为 VideoView 视图设置媒体控制器，设置了之后就会自动由进度条、前进、后退等操作
         */
        videoView_online.setMediaController(new MediaController(this));

        /**视频准备完成时回调
         * */
        videoView_online.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i("tag", "--------------视频准备完毕,可以进行播放.......");
            }
        });
        /**
         * 视频播放完成时回调
         */
        videoView_online.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i("tag", "------------------视频播放完毕..........");
            }
        });

        /**
         * 视频播放发送错误时回调
         */
        videoView_online.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i("tag", "---------------------视频播放失败...........");
                return false;
            }
        });

        /**开始播放视频
         * */
        videoView_online.start();
    }


}