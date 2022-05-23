package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.web_view);
        ActionBar ac = getSupportActionBar();
        if (ac != null) {
            ac.setTitle("WebView");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        Button btn_load_href=findViewById(R.id.btn_load_href);
        Button btn_load_html=findViewById(R.id.btn_load_html);
        Button btn_load_code=findViewById(R.id.btn_load_code);


        btn_load_href.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebSettings webSettings = webView.getSettings();

                webSettings.setJavaScriptEnabled(true);
                //请求加载百度，并交由Webclient去处理
                //使用WebViewClient设置监听并处理WebView的请求事件
                webView.loadUrl("http://www.baidu.com");
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        //根据url真正去加载网页的操作
                        view.loadUrl(url);
                        //在当前WebView中打开网页，而不在浏览器中
                        return true;
                    }
                });
            }
        });

        btn_load_html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebSettings webSettings = webView.getSettings();

                webSettings.setJavaScriptEnabled(true);
                //请求加载百度，并交由Webclient去处理
                //使用WebViewClient设置监听并处理WebView的请求事件
                webView.loadUrl("file:///android_asset/test_html.html");
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        //根据url真正去加载网页的操作
                        view.loadUrl(url);
                        //在当前WebView中打开网页，而不在浏览器中
                        return true;
                    }
                });

            }
        });


        btn_load_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String body = "示例：这里有个img标签，地址是相对路径<img src='/uploads/allimg/130923/1FP02V7-0.png' />";
                webView.loadDataWithBaseURL("http://www.jcodecraeer.com", body, "text/html", "utf-8",null);
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        //根据url真正去加载网页的操作
                        view.loadUrl(url);
                        //在当前WebView中打开网页，而不在浏览器中
                        return true;
                    }
                });
            }
        });


        //方式一：加载一个网页


        //方式二：加载应用资源文件内的网页
//        webView.loadUrl("file:///android_asset/test.html");

        //方式三：加载一段代码
//        webView.loadData(String data,String mimeType, String encoding);
//获取WebSettings类的实例，此类用于对WebView加载的网页进行设置

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
                startActivity(new Intent(WebViewActivity.this, HrefWebView.class).putExtra("url", "" +
                        "https://blog.csdn.net/vicwudi/article/details/81990467?ops_re" +
                        "quest_misc=%257B%2522request%255Fid%2522%253A%2522165310226516" +
                        "781667849552%2522%252C%2522scm%2522%253A%252220140713.13010233" +
                        "4..%2522%257D&request_id=165310226516781667849552&biz_id=0&utm_" +
                        "medium=distribute.pc_search_res" +
                        "ult.none-task-blog-2~all~sobaiduend~default-2-81990467-null-nu" +
                        "ll.142^v10^control,157^v4^control&utm_term=webview&spm=" +
                        "1018.2226.3001.4187").putExtra("title", "WebView详解"));
                return false;

            }
        });

        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }

}