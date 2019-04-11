package com.soft.zkrn.okhttputil_tester;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class Main2Activity extends AppCompatActivity {

    private Button bt_post;
    private Button bt_next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bt_post = findViewById(R.id.btn_post);
        bt_next = findViewById(R.id.btn_next);

        bt_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromPost();
            }
        });

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,Main3Activity.class));
            }
        });

    }

    private void getDataFromPost() {
        new Thread()
        {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                FormBody body=new FormBody.Builder()
                        .add("userName" ,"postTester01")
                        .add("userPassword","password01")
                        .add("userPhonenumber","1145141919")
                        .build();
                String s3 ="";
                Log.d(s3, String.valueOf(body));
                Request request=new Request.Builder().post(body).url("http://www.xinxianquan.xyz:8080/zhaqsq/user/register").build();
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
