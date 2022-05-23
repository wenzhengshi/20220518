package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20220518.technology.Technology;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置ActionBar
        ActionBar ac = getSupportActionBar();
        if (ac != null) {
            ac.setTitle("技术和组件演示");
        }
        ListView listView = findViewById(R.id.technology);
        Technology technology = new Technology();
        String technology_data[] = {
                //布局
                technology.getLinearLayout(),//ok
                technology.getConstraintLayout(),
                technology.getAbsoluteLayout(),
                technology.getLinearLayoutCompat(),
                technology.getPercentFrameLayout(),
                technology.getFrameLayout(),
                technology.getViewPager(),
                technology.getTableLayout(),
                technology.getSwipeRefreshLayout(),

                //组件
                technology.getActivity_knowledge(),
                technology.getService_knowledge(),
                technology.getFragment_knowledge(),


                //视图view
                technology.getScrollView(),
                technology.getGridView(),
                technology.getVideoView(),
                technology.getMusic(),
                technology.getRecord_Audio(),
                technology.getListView(),
                technology.getImageView(),
                technology.getTextView(),
                technology.getRecyclerView(),
                technology.getSearchView(),
                technology.getWebView(),
                technology.getHorizontalScrollView(),
                technology.getExpandableListView(),
                technology.getAutoCompleteTextView(),


                //控件
                technology.getButton(),
                technology.getCheckBox(),
                technology.getBroadcast_receivers_knowledge(),
                technology.getAlertdialog(),
                technology.getActionbar(),
                technology.getContextMenu(),
                technology.getOptionMenu(),
                technology.getSubMenu(),
                technology.getEditText(),
                technology.getKeyBoard(),
                technology.getDatePicker(),
                technology.getTimePicker(),
                technology.getSwitch_on_off(),
                technology.getImageSwitcher(),
                technology.getViewSwitcher(),
                technology.getNotification(),
                technology.getTabHost(),
                technology.getTableHost(),
                technology.getListPopupWindow(),
                technology.getSeekBar(),
                technology.getProgressBar(),
//                technology.getToolBar(),
//                technology.getStatus_BAR(),
//                technology.getToggleButton(),
//                technology.getRadioButton(),
//                technology.getZoomButton(),
//                technology.getTextClock(),
//                technology.getGallery(),
//
//
//                //权限
//                technology.getPermission_ACCESS_COARSE_LOCATION(),
//                technology.getPermission_ACCESS_FINE_LOCATION(),
//                technology.getPermission_ACCESS_LOCATION_EXTRA_COMMANDS(),
//                technology.getPermission_CONTROL_LOCATION_UPDATES(),
//                technology.getPermission_INSTALL_LOCATION_PROVIDER(),
//                technology.getPermission_ACCESS_NETWORK_STATE(),
//                technology.getPermission_CHANGE_NETWORK_STATE(),
//                technology.getPermission_ACCESS_WIFI_STATE(),
//                technology.getPermission_CHANGE_WIFI_MULTICAST_STATE(),
//                technology.getPermission_CHANGE_WIFI_STATE(),
//                technology.getPermission_BLUETOOTH(),
//                technology.getPermission_BLUETOOTH_ADMIN(),
//                technology.getSaveCameraPhoto(),
//                technology.getPermission_CAMERA(),
//                technology.getRead_SMS(),
//                technology.getReceive_SMS(),
//                technology.getSend_SMS(),
//                technology.getPermission_BROADCAST_SMS(),
//                technology.getWrite_sms(),
//                technology.getSet_ANIMATION_SCALE(),
//                technology.getWake_lock(),
//                technology.getPermission_BROADCAST_PACKAGE_REMOVED(),
//                technology.getPermission_BROADCAST_WAP_PUSH(),
//                technology.getPermission_BROADCAST_SMS(),
//                technology.getSet_DEBUG_APP(),
//                technology.getWrite_APN_SETTINGS(),
//                technology.getPermission_MODIFY_AUDIO_SETTINGS(),
//                technology.getWrite_settings(),
//                technology.getWrite_secure_settings(),
//                technology.getPermission_BATTERY_STATS(),
//                technology.getSystem_ALERT_WINDOW(),
//                technology.getPermission_FORCE_BACK(),
//                technology.getPermission_KILL_BACKGROUND_PROCESSES(),
//                technology.getSubscribed_FEEDS_WRITE(),
//                technology.getRead_write_File(),
//                technology.getWrite_gServices(),
//                technology.getRecord_Audio(),
//                technology.getPermission_MODIFY_AUDIO_SETTINGS(),
//                technology.getFilter_knowledge(),
//                technology.getVibrate(),
//                technology.getPermission_NFC(),
//                technology.getPermission_DELETE_CACHE_FILES(),
//                technology.getPermission_MOUNT_FORMAT_FILESYSTEMS(),
//                technology.getPermission_MOUNT_UNMOUNT_FILESYSTEMS(),
//                technology.getRead_CONTACTS(),
//                technology.getWrite_contacts(),
//                technology.getContent_providers()

        };

        MyAdapter<String> ad = new MyAdapter<String>(this, android.R.layout.simple_list_item_1, technology_data);


        listView.setAdapter(ad);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, SelfActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ConstraintLayout_Mine.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, AbsoluteLayout.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, LinearLayoutCompat_Mine.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, PercentFrameLayout_Mine.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, FrameLayout_Mine.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, ViewPager_Mine.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, TableLayout_Mine.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, SwipeRefreshLayout_Mine.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, TestActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, ServiceActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, FragmentActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, ScrollViewActivity.class));
                        break;
                    case 13:
                        startActivity(new Intent(MainActivity.this, GridViewActivity.class));
                        break;
                    case 14:
                        startActivity(new Intent(MainActivity.this, VideoViewActivity.class));
                        break;
                    case 15:
                        startActivity(new Intent(MainActivity.this, MusicActivity.class));
                        break;
                    case 16:
                        startActivity(new Intent(MainActivity.this, RecodAudioActivity.class));
                        break;
                    case 17:
                        if (checkPermission()) {
                            startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                        }
                        break;
                    case 18:
                        startActivity(new Intent(MainActivity.this, ImageViewActivity.class));
                        break;
                    case 19:
                        startActivity(new Intent(MainActivity.this, TextViewActivity.class));
                        break;
                    case 20:
                        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                        break;
                    case 21:
                        startActivity(new Intent(MainActivity.this, SearchViewActivity.class));
                        break;
                    case 22:
                        startActivity(new Intent(MainActivity.this, WebViewActivity.class));
                        break;
                    case 23:
                        startActivity(new Intent(MainActivity.this, ScrollViewActivity.class));
                        break;
                    case 24:
                        startActivity(new Intent(MainActivity.this, ExpandableListviewActivity.class));
                        break;
                    case 25:
                        startActivity(new Intent(MainActivity.this, AutoCompleteTexviewActivity.class));
                        break;
                    case 26:
                        startActivity(new Intent(MainActivity.this, ButtonActivity.class));
                        break;
                    case 27:
                        startActivity(new Intent(MainActivity.this, CheckBoxActivityMine.class));
                        break;
                    case 28:
                        startActivity(new Intent(MainActivity.this, BroadcastReceiverActivity.class));
                        break;
                    case 29:
                        startActivity(new Intent(MainActivity.this, AlertDialogActivity.class));
                        break;
                    case 30:
                        startActivity(new Intent(MainActivity.this, ActionBarActivity.class));
                        break;
                    case 31:
                        startActivity(new Intent(MainActivity.this, ContextMenuActivity.class));
                        break;
                    case 32:
                        startActivity(new Intent(MainActivity.this, OptionMenuActivity.class));
                        break;
                    case 33:
                        startActivity(new Intent(MainActivity.this,SubMenuActivity.class));
                        break;
                    case 34:
                        startActivity(new Intent(MainActivity.this,EditActivity.class));
                        break;
                    case 35:
                        startActivity(new Intent(MainActivity.this,KeyBoardActivity.class));
                        break;
                    case 36:
                        startActivity(new Intent(MainActivity.this,DataPickerActivity.class));
                        break;
                    case 37:
                        startActivity(new Intent(MainActivity.this,TimePickerActivity.class));
                        break;
                    case 38:
                        startActivity(new Intent(MainActivity.this,SwitchActivty.class));
                        break;
                    case 39:
                        startActivity(new Intent(MainActivity.this,ImageSwitherActivity.class));
                        break;
                    case 40:
                        startActivity(new Intent(MainActivity.this,ViewSwitcherActivity.class));
                        break;
                    case 41:
                        startActivity(new Intent(MainActivity.this,NotificationActivity.class));
                        break;
                    case 42:
                        startActivity(new Intent(MainActivity.this,TabHostActivity.class));
                        break;
                    case 43:
                        startActivity(new Intent(MainActivity.this,TableHostActivity.class));
                        break;
                    case 44:
                        startActivity(new Intent(MainActivity.this,PopupWindowActivity.class));
                        break;
                    case 45:
                        startActivity(new Intent(MainActivity.this,SeekBarActivity.class));
                        break;
                    case 46:
                        startActivity(new Intent(MainActivity.this,ProgressBarActivity.class));
                        break;



                }
            }
        });
    }

    /**
     * 相机权限申请
     */
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, permissions, 200);
                    return false;
                }
            }
        } else {
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

    private class MyAdapter<T> extends ArrayAdapter {

        public MyAdapter(Context context, int resource, Object[] objects) {
            super(context, resource, objects);
        }

//        MyListener listener = new MyListener();

        //重载getView函数，等于说该函数完全接管ArrayAdapter的设置TextView操作
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String str = (String) getItem(position);//通过position获取当前要赋值的内容
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
            tv.setText(str);//设置TextView中的字符串
            tv.setTextSize(20);//设置大小
//            tv.setOnClickListener(listener);//为每个TextView加载监听函数
            return convertView;
        }

//        //MyListener类继承OnClickListener，用来监听每个Item的点击事件
//        private class MyListener implements View.OnClickListener {
//            TextView lastTv = null;//记录上一个变色的TextView
//
//            @Override
//            public void onClick(View v) {
//                TextView tv = (TextView) v;
//                if (lastTv != null) {//若lastTv不为空则要将lastTv颜色和字体大小初始化
//                    lastTv.setTextSize(20);
//                    lastTv.setBackgroundColor(Color.WHITE);
//                }
//                tv.setTextSize(20);
//                tv.setBackgroundColor(Color.WHITE);
//                lastTv = tv;//保存点击的TextView
//            }
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, 0, 1, "Sqlite数据库").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//
                startActivity(new Intent(MainActivity.this,SqliteActivty.class));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }


}