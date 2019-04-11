package com.soft.zkrn.okhttputil_tester;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.soft.zkrn.okhttputil_tester.okhttp.CallBackUtil;
import com.soft.zkrn.okhttputil_tester.okhttp.OkhttpUtil;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.MediaType;

public class Main5Activity extends AppCompatActivity {

    Gson gson = new Gson();

    private Button bt_get;
    private Button bt_TruePost;
    private Button bt_WrongPost;
    private TextView tv_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        bt_get = findViewById(R.id.bt_geter);
        bt_TruePost = findViewById(R.id.bt_poster_1);
        bt_WrongPost = findViewById(R.id.bt_poster_2);

        tv_display = findViewById(R.id.tv_display);

        bt_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.xinxianquan.xyz:8080/zhaqsq/call/calls";
                HashMap<String,String> paramsMap = new HashMap<>();
                paramsMap.put("pn","2");
                OkhttpUtil.okHttpGet(url, paramsMap,new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {}//请求失败时调用，执行在UI线程

                    @Override
                    public void onResponse(String response) {//请求成功时调用，执行在UI线程

                        TaskData stringsimple = gson.fromJson(response,TaskData.class);
                        String s = "";
                        s = s + stringsimple.getcode() + '\n';
                        s = s + stringsimple.getmsg() + '\n';

                        s = s + stringsimple.getextend().getpageinfo().getpageNum() + '\n';
                        s = s + stringsimple.getextend().getpageinfo().getpageSize() + '\n';
                        s = s + stringsimple.getextend().getpageinfo().getendRow() + '\n';
                        s = s + stringsimple.getextend().getpageinfo().getpages() + "\n\n";
//
                        s = s + "id为" + stringsimple.getextend().getpageinfo().getlist().get(0).getcallId() + '\n';
                        s = s + "停止时间为" + stringsimple.getextend().getpageinfo().getlist().get(0).getendTime() + '\n';
                        s = s + "金额为" + stringsimple.getextend().getpageinfo().getlist().get(0).getcallMoney() + '\n';

                        tv_display.setText(s);
                    }
                });
            }
        });

        bt_TruePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.xinxianquan.xyz:8080/zhaqsq/user/login";
                HashMap<String, String> paramsMap = new HashMap<>();
                paramsMap.put("userName" ,"postTester01");
                paramsMap.put("userPassword","password01");
                OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {
                        String s1 = "";
                        Log.d(s1,"post失败了");
                    }

                    @Override
                    public void onResponse(String response) {
                        processJson(response);
                    }
                });
            }
        });

        bt_WrongPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.xinxianquan.xyz:8080/zhaqsq/user/login";
                HashMap<String, String> paramsMap = new HashMap<>();
                paramsMap.put("userName" ,"postTester01");
                paramsMap.put("userPassword","password00");
                OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {
                        String s1 = "";
                        Log.d(s1,"post失败了");
                    }

                    @Override
                    public void onResponse(String response) {
                        processJson(response);

                    }
                });
            }
        });
    }

    private void processJson(String res){
        Json_Login login_json = gson.fromJson(res,Json_Login.class);
        String s_json ="";
        s_json = s_json + "Msg为" + login_json.getMsg() + '\n';
        s_json = s_json + "Code为" + login_json.getCode() + '\n';
        if(login_json.getCode() != 100){
            tv_display.setText("登录失败");
        }else{
            s_json = s_json + "用户名为" + login_json.getExtend().getUser().getUserName() + '\n';
            s_json = s_json + "用户性别为" + login_json.getExtend().getUser().getUserSex() + '\n';
            tv_display.setText(s_json);
        }
    }
}
