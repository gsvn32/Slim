package com.example.lenovo.slim;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by LENOVO on 10/19/2018.
 */

public class usersingleton {
    private static usersingleton uinst;
    private static Context mctx;
    private RequestQueue rq;
    public usersingleton(Context con){
        mctx=con;
        rq=getRq();

    }

    public RequestQueue getRq() {
        if(rq==null){
            rq= Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return rq;
    }

    public static synchronized usersingleton getInstance(Context con) {
        if(uinst==null){
            uinst=new usersingleton(con);
        }
        return uinst;
    }
    public <T> void addtorequest(Request<T> req){
        getRq().add(req);
    }
}
