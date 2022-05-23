package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextSwitcher;

import com.example.a20220518.service.MediaService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener,
        Runnable, ServiceConnection, SeekBar.OnSeekBarChangeListener {



        private ImageView disc;
        private ObjectAnimator discAnimation;//自定义指针和唱盘
        private boolean isPlaying = true;//判断是否处于播放状态

        //声明服务
        private MediaService.MusicController mMusicController;
        //使用方法：mMusicController.play();播放   mMusicController.pause();暂停
        private boolean running;
        private TextSwitcher mSwitcher;
        private SeekBar mSeekBar;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_music);


            ActionBar ac=getSupportActionBar();
            if(ac!=null){
                ac.setTitle("音频MusicController");
                ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

            }

            //启动service




            //滑动条部分
            mSeekBar = (SeekBar) findViewById(R.id.music_seek_bar);
            mSeekBar.setOnSeekBarChangeListener(this);
            mSwitcher = (TextSwitcher) findViewById(R.id.text_switcher);
            mSwitcher.setInAnimation(this, android.R.anim.fade_in);
            mSwitcher.setOutAnimation(this, android.R.anim.fade_out);
            Intent intent = new Intent(this, MediaService.class);
            //增加StartService，来增加后台播放功能
            startService(intent);
            // 绑定服务，使用context来绑定
            // 那个界面需要绑定 就用哪个 Activity
            // 参数1：Intent               代表需要绑定哪一个Service
            // 参数2：ServiceConnection    回调接口，可以接收到Service连接成功和断开的回调，成功就可以取到对象。
            // 绑定服务 参数2就是服务和指定的对象绑定在一起
            bindService(intent, this, BIND_AUTO_CREATE);

            //指针和唱片部分
            initViews();//定义背景图
            setAnimations();
        }
        private void initViews() {
            Button playingPre = (Button) findViewById(R.id.playing_pre);
            Button playingPlay = (Button) findViewById(R.id.playing_play);
            Button playingNext = (Button) findViewById(R.id.playing_next);
            disc = (ImageView) findViewById(R.id.disc);
            playingPre.setOnClickListener(this);
            playingPlay.setOnClickListener(this);
            playingNext.setOnClickListener(this);
        }
        //动画设置
        private void setAnimations() {
            discAnimation = ObjectAnimator.ofFloat(disc, "rotation", 0, 360);
            discAnimation.setDuration(20000);
            discAnimation.setInterpolator(new LinearInterpolator());
            discAnimation.setRepeatCount(ValueAnimator.INFINITE);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.playing_pre://前一曲
                    if (discAnimation != null) {
                        discAnimation.end();
                        playing();
                    }
                    break;
                case R.id.playing_play://播放中
                    if (isPlaying){
                        playing();
                    }else {
                        if (discAnimation != null && discAnimation.isRunning()) {
                            discAnimation.cancel();
                            mMusicController.pause();
                            float valueAvatar = (float) discAnimation.getAnimatedValue();
                            discAnimation.setFloatValues(valueAvatar, 360f + valueAvatar);
                        }
                        isPlaying = true;
                    }
                    break;
                case R.id.playing_next://下一曲
                    if (discAnimation != null) {
                        discAnimation.end();
                        playing();
                    }
                    break;
                default:
                    break;
            }
        }

        //播放：1、播放音乐 2、动画旋转 3、暂停图片切换为播放按钮图片
        private void playing(){
            discAnimation.start();
            mMusicController.play();//播放
            isPlaying = false;
        }
        //===================================播放歌曲服务开启、停止、结束===============================
        @Override
        protected void onStart() {
            super.onStart();
            Thread thread = new Thread(this);
            thread.start();
        }

        @Override
        protected void onStop() {
            running = false;
            super.onStop();
        }

        @Override
        protected void onDestroy() {
            // 解除绑定
            unbindService(this);
            super.onDestroy();
        }

        //---------------------播放到当前音乐的滑动条及时间设置--------------------------
        @Override
        public void run() {
            running = true;
            try {
                while (running) {
                    if (mMusicController != null) {
                        long musicDuration = mMusicController.getMusicDuration();
                        final long position = mMusicController.getPosition();
                        final Date dateTotal = new Date(musicDuration);
                        final SimpleDateFormat sb = new SimpleDateFormat("mm:ss");
                        mSeekBar.setMax((int) musicDuration);
                        mSeekBar.setProgress((int) position);
                        mSwitcher.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        Date date = new Date(position);
                                        String time = sb.format(date) + "/" + sb.format(dateTotal);
                                        mSwitcher.setCurrentText(time);
                                    }
                                }
                        );
                    }

                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //-----------------------------
        //服务绑定与解除绑定的回调

        /**
         * 当服务与当前绑定对象，绑定成功，服务onBind方法调用并且返回之后
         * 回调给这个方法
         *
         * @param name
         * @param service IBinder 就是服务 onBind 返回的对象
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMusicController = ((MediaService.MusicController) service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMusicController = null;
        }

        public void btnStopService(View view) {
            Intent intent = new Intent(this, MediaService.class);
            stopService(intent);
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mMusicController.setPosition(seekBar.getProgress());
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
                startActivity(new Intent(MusicActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://www.freesion.com/article/4046250806/").putExtra("title","MusicController"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }

}