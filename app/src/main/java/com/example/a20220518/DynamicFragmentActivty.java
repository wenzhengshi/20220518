package com.example.a20220518;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.a20220518.my_fragment.MyFragment1;
import com.example.a20220518.my_fragment.MyFragment3;
import com.example.a20220518.my_fragment.Myfragment2;

public class DynamicFragmentActivty extends AppCompatActivity implements View.OnClickListener {


    private Button lianxiren;
    private Button duanxin;
    private Button dianhua;

    private FragmentManager fragmentManager;


    private FragmentTransaction fragmentTransaction;
    private MyFragment1 lianxirenFragment;
    private Myfragment2 dianhuaFragment;
    private MyFragment3 duanxinFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment_activty);
        //设置ActionBar
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("动态加载的Fragment碎片");
            ac.setDisplayHomeAsUpEnabled(true);  // 设置返回按钮

        }

        //提前加载
        addToIndex();


        bind();
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();


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


    private void bind() {
        lianxiren=findViewById(R.id.lainxiren);
        duanxin=findViewById(R.id.duanxin);
        dianhua=findViewById(R.id.dianhua);
        lianxiren.setOnClickListener(this);
        duanxin.setOnClickListener(this);
        dianhua.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        fragmentTransaction=fragmentManager.beginTransaction();
        switch (view.getId()){
            case R.id.lainxiren:
                if(lianxirenFragment==null){
                    lianxirenFragment=new MyFragment1();
                }
                fragmentTransaction.replace(R.id.frame,lianxirenFragment);
                break;
            case R.id.dianhua:
                if(dianhuaFragment==null){
                    dianhuaFragment=new Myfragment2();
                }
                fragmentTransaction.replace(R.id.frame,dianhuaFragment);
                break;
            case R.id.duanxin:
                if(duanxinFragment==null){
                    duanxinFragment=new MyFragment3();
                }
                fragmentTransaction.replace(R.id.frame,duanxinFragment);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    //提前加载
    private  void addToIndex(){
        bind();
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        if(lianxirenFragment==null){
            lianxirenFragment=new MyFragment1();
        }
        fragmentTransaction.replace(R.id.frame,lianxirenFragment);


        fragmentTransaction.commit();
    }

}