package com.soft.zkrn.okhttputil_tester;

public class AddTask {

    private int callId;//？？

    private int subId;//发布人id
    private long subTime;//发布时间
    private long endTime;//截止时间
    private String callTitle;//标题
    private String callDesp;//描述
    private int callMoney;//金额
    private String callNow;//状态
    private int recId;//接收人id
    private String subName;//发布人昵称
    private String recName;//接收人昵称

//subId
//subTime
//endTime
//callTitle
//callDesp
//callMoney
//callNow
//recId
//subName
//recName

//    private String callAddress;//发布人地址


    AddTask(int callId, int subId, long subTime, long endTime, String callTitle, String callDesp, int callMoney, String callNow, int recId, String subName, String recName){//,String callAddress){
        this.callId = callId;

        this.subId = subId;
        this.subTime = subTime;
        this.endTime = endTime;
        this.callTitle = callTitle;
        this.callDesp = callDesp;
        this.callMoney = callMoney;
        this.callNow = callNow;
        this.recId = recId;
        this.subName = subName;
        this.recName = recName;
//        this.callAddress = callAddress;
    }


    public void setcallId(int callId){this.callId = callId;}

    public void setsubId(int subId){this.subId = subId ;}
    public void setsubTime(long subTime){this.subTime = subTime;}
    public void setendTime(long endTime){this.endTime = endTime;}
    public void setcallTitle(String callTitle){this.callTitle = callTitle;}
    public void setcallDesp(String callDesp){this.callDesp = callDesp;}
    public void setcallMoney(int callMoney){this.callMoney = callMoney;}
    public void setcallNow(String callNow){this.callNow = callNow;}
    public void setrecId(int recId){this.recId = recId;}
    public void setsubName(String subName){this.subName = subName;}
    public void setrecName(String recName){this.recName = recName;}
//    public void setcallAddress(String callAddress){this.callAddress = callAddress;}

}
