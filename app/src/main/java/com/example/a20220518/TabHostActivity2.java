package com.example.a20220518;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class TabHostActivity2 extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("TabDemoActivity");
        TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.activity_tab_host2,
                tabHost.getTabContentView(), true);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("tab1", getResources().getDrawable(R.drawable.ic_launcher_background))
                .setContent(R.id.view1));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("tab2")
                .setContent(R.id.view2));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("tab3")
                .setContent(R.id.view3));


        //标签切换事件处理，setOnTabChangedListener
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equals("tab1")) {   //第一个标签
                }
                if (tabId.equals("tab2")) {   //第二个标签
                }
                if (tabId.equals("tab3")) {   //第三个标签
                }
            }
        });


    }

}

