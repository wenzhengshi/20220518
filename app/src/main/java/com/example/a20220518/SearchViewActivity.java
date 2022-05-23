package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class SearchViewActivity extends AppCompatActivity {


    private String[] strings = new String[]{"111","222","333"};
    private ListView list;
    private SearchView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("SearchView");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }
        list=findViewById(R.id.list);
        search=findViewById(R.id.search);
        list.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                strings));
//        设置ListView启动过滤
        list.setTextFilterEnabled(true);
//        设置该SearchView默认是否自动缩小为图标
        search.setIconifiedByDefault(false);
//        设置该SearchView显示搜索图标
        search.setSubmitButtonEnabled(true);
//        设置该SearchView内默认显示的搜索文字
        search.setQueryHint("查找");
//        为SearchView组件设置事件的监听器
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //            单击搜索按钮时激发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
//                实际应用中应该在该方法内执行实际查询
//                此处仅使用Toast显示用户输入的查询内容
                Toast.makeText(SearchViewActivity.this,"您选择的是："+query,
                        Toast.LENGTH_SHORT).show();
                return false;
            }
            //            用户输入时激发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
//                如果newText不是长度为0的字符串
                if (TextUtils.isEmpty(newText)){
//                    清除ListView的过滤
                    list.clearTextFilter();
                }else {
//                    使用用户输入的内容对ListView的列表项进行过滤
                    list.setFilterText(newText);
                }
                return true;
            }
        });
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
                startActivity(new Intent(SearchViewActivity.this,HrefWebView.class).putExtra("url","https://blog" +
                        ".csdn.net/weixin_46157140/article/details/108213511?ops_request_misc=%257B%2522request%255Fid%" +
                        "2522%253A%2522165310125316781435417349%2522%252C%2522scm%2522%253A%2522" +
                        "20140713.130102334..%2522%257D&request_id=1653101253167814354" +
                        "17349&biz_id=0&utm_medium=distribute.pc_search_result." +
                        "none-task-blog-2~all~top_positive~default-1-1082135" +
                        "11-null-null.142^v10^control,157^v4^control&utm_term=searchView" +
                        "&spm=1018.2226.3001.4187").putExtra("title","SearchView"));
                return false;

            }

        });


        menu.add(0, 1, 2, "取消");

        return super.onCreateOptionsMenu(menu);

    }

}

