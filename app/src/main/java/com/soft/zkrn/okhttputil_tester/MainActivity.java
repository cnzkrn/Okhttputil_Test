package com.soft.zkrn.okhttputil_tester;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;


import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{

    private OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private Button btn_json;
    private Button btn_get;
    private Button bt_turn;
    private TextView tv_tips1;
    private TextView tv_json;
    private TextView tv_tips2;
    private TextView tv_gson;

    /**      * get请求      */
    private static final int GET = 1;

    /**      * post请求      */
    private static final int POST = 2;

    /**      * 导入gson      */
    Gson gson = new Gson();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //获取数据 //
            TaskData stringsimple = gson.fromJson((String) msg.obj, TaskData.class);
            tv_tips1.setText("Json内容为：");
            tv_json.setText((String) msg.obj);
            tv_tips2.setText("Gson转化为：");
            String s = "";
            s = s + stringsimple.getcode() + '\n';
            s = s + stringsimple.getmsg() + '\n';
            String s1 = "";
            s = s + stringsimple.getextend().getpageinfo().getpageNum() + '\n';
            s = s + stringsimple.getextend().getpageinfo().getpageSize() + '\n';
            s = s + stringsimple.getextend().getpageinfo().getendRow() + '\n';
            s = s + stringsimple.getextend().getpageinfo().getpages() + "\n\n";
            s = s + "id为" + stringsimple.getextend().getpageinfo().getlist().get(0).getcallId() + '\n';
            s = s + "停止时间为" + stringsimple.getextend().getpageinfo().getlist().get(0).getendTime() + '\n';
            s = s + "金额为" + stringsimple.getextend().getpageinfo().getlist().get(0).getcallMoney() + '\n';
            tv_gson.setText(s.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_tips1 = findViewById(R.id.tv_tips1);
        tv_json = findViewById(R.id.tv_json);
        tv_tips2 = findViewById(R.id.tv_tips2);
        tv_gson = findViewById(R.id.tv_gson);
        btn_json = findViewById(R.id.btn_json);
        btn_get = findViewById(R.id.btn_get);
        bt_turn = findViewById(R.id.btn_turn);

        btn_json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main5Activity.class));
            }
        });
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonGetByOkHttp();
            }
        });
        bt_turn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
    }
    /**
     * get请求
     *
     *
     **/
    private String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     *
     * 使用get请求网络数据
     *
     **/
    private void getDataFromGet() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String result = get("http://www.xinxianquan.xyz:8080/zhaqsq/call/calls");
                    Message msg = Message.obtain();
                    msg.what = GET;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }         }.start();

    }
    private void jsonGetByOkHttp() {
        getDataFromGet();
    }
}

