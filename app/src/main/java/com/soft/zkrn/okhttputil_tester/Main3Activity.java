package com.soft.zkrn.okhttputil_tester;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main3Activity extends AppCompatActivity {

    private Button bt_po;
    private Button bt_wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        bt_po = findViewById(R.id.btn_post2);
        bt_wrong = findViewById(R.id.bt_wrong);

        bt_po.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromPost();
            }
        });

        bt_wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromPost(1);
            }
        });

    }


    /**
     *
     *  使用post请求网络数据
     *
     **/

    private void getDataFromPost() {
        new Thread()
        {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                FormBody body=new FormBody.Builder()
                        .add("userName" ,"postTester01")
                        .add("userPassword","password01")
                        .build();
                String s3 ="";
                Log.d(s3,String.valueOf(body));
                Request request=new Request.Builder().post(body).url("http://www.xinxianquan.xyz:8080/zhaqsq/user/login").build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        String s ="";
                        Log.d(s,"失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String s ="";
                        final String res = response.body().string();
                        Log.d(s,res);
                    }
                });
            }
        }.start();
    }

    private void getDataFromPost(int i) {
        new Thread()
        {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                FormBody body=new FormBody.Builder()
                        .add("userName" ,"postTester01")
                        .add("userPassword","password00")
                        .build();
                String s3 ="";
                Log.d(s3,String.valueOf(body));
                Request request=new Request.Builder().post(body).url("http://www.xinxianquan.xyz:8080/zhaqsq/user/login").build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        String s ="";
                        Log.d(s,"失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String s ="";
                        final String res = response.body().string();
                        Log.d(s,res);
                    }
                });
            }
        }.start();
    }

}
