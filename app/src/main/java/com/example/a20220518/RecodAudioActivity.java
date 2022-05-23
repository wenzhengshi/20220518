package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class RecodAudioActivity extends AppCompatActivity {

    private  MediaRecorder recorder ;
    private  Button btn_start_record;
    private  Button btn_pause_record;
    private  Button btn_stop_record;
    private TextView textView_show_state;
    private String mFileName;
    private String mFilePath;
    public static final String FILE_NAME = "FILE_NAME";
    public static final String FILE_PATH = "FILE_PATH";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recod_audio);

        ActionBar ac = getSupportActionBar();
        if (ac != null) {
            ac.setTitle("MediaRecorder 和AudioRecord");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        btn_start_record=findViewById(R.id.btn_start_record);
        btn_pause_record=findViewById(R.id.btn_pause_record);
        btn_stop_record=findViewById(R.id.btn_stop_record);
        textView_show_state=findViewById(R.id.record_show_state);

        //开始录音按钮
        btn_start_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果取得权限
                if(checkPermission()) {
                    textView_show_state.setText("录音已开始");
                    btn_stop_record.setEnabled(true);
                    btn_start_record.setEnabled(false);
                    recorder = new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);

                    recorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);

                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                    recorder.setAudioChannels(1);

                    recorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/SoundRecorder/" + System.currentTimeMillis() + ".aac");
                    try {


                        recorder.prepare();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    recorder.start();

                }
//                recorder.release(); // Now the object cannot be reused

            }
        });
        //暂停录音按钮
        btn_pause_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //停止录音按钮
        btn_stop_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_stop_record.setEnabled(false);
                recorder.stop();
                btn_start_record.setEnabled(true);
                textView_show_state.setText("已停止录音");
            }
        });


    }







    /**
     * 权限申请
     */
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE};
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, permissions, 200);
                    btn_start_record.setEnabled(true);
                    return false;
                }
            }
        }else{
            return true;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && requestCode == 200) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivityForResult(intent, 200);
                    return;
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 200) {
            checkPermission();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, 0, 1, "查看教程").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//
                startActivity(new Intent(RecodAudioActivity.this,HrefWebView.class).putExtra("url","" +
                        "https://blog.csdn.net/zeqiao/article/details/84303114?ops_req" +
                        "uest_misc=%257B%2522request%255Fid%2522%253A%252216" +
                        "5297720016780357256972%2522%252C%2522scm%2522%253A%25222" +
                        "0140713.130102334..%2522%257D&request_id=16529772001" +
                        "6780357256972&biz_id=0&utm_medium=distribute.pc_search_result.non" +
                        "e-task-blog-2~all~sobaiduend~default-1-84303114-null" +
                        "-null.142^v10^control,157^v4^control&utm_term=MediaR" +
                        "ecorder录音&spm=1018.2226.3001.4187").putExtra("title","录音MediaRecord"));
                return false;

            }
        });
        menu.add(0, 1, 2, "AudioRecord").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//
                startActivity(new Intent(RecodAudioActivity.this,AudioRecordActivity.class));
                return false;

            }
        });
        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }





}