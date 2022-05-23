package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class HrefWebView extends AppCompatActivity {
    private  AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_href_web_view);
        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle(getIntent().getStringExtra("title"));
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(HrefWebView.this,R.style.MyDialog);
        alterDiaglog.setView(R.layout.dialog_1);//加载进去
        AlertDialog dialog = alterDiaglog.create();
        //显示
//        dialog.setCancelable(false);
        dialog.show();

        WebView webView = (WebView) findViewById(R.id.web);
        //获取WebSettings类的实例，此类用于对WebView加载的网页进行设置
        WebSettings webSettings = webView.getSettings();
        //使WebView可以使用JavaScript
        webSettings.setJavaScriptEnabled(true);
        //请求加载百度，并交由Webclient去处理

//        //使用WebViewClient设置监听并处理WebView的请求事件
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//
//                //根据url真正去加载网页的操作
//                view.loadUrl(url);
//                //在当前WebView中打开网页，而不在浏览器中
//                return true;
//            }
//        });
        webView.setWebViewClient(new WebViewClient()

        {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                dialog.cancel();
                webView.setVisibility(View.VISIBLE);
                super.onPageFinished(view, url);
            }
        });
        webView.loadUrl(getIntent().getStringExtra("url"));


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

        menu.add(0, 0, 1, "分享链接").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//
                copyContentToClipboard(getIntent().getStringExtra("url"),HrefWebView.this);
                return false;

            }
        });
        menu.add(0, 1, 2, "浏览器打开").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//
                String valida = getIntent().getStringExtra("url");

                Intent i = new Intent(Intent.ACTION_VIEW);

                i.setData(Uri.parse(valida));

                startActivity(i);
                return false;

            }
        });

        menu.add(0, 2, 3, "取消");

        return super.onCreateOptionsMenu(menu);

    }


    /**
     * 复制内容到剪贴板
     *
     * @param content
     * @param context
     */
    public void copyContentToClipboard(String content, Context context) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", content);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
        Toast.makeText(context, "链接已复制", Toast.LENGTH_SHORT).show();
    }

}