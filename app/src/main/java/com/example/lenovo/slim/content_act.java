package com.example.lenovo.slim;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by LENOVO on 9/23/2018.
 */

public class content_act extends Fragment {


    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    RecyclerView.LayoutManager lm;
ArrayList<exercise> alist=new ArrayList<>();
    String te;
    private String msg="content";
    private String msg1="before";
    private String username="nikhil32";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View temp = inflater.inflate(R.layout.content_main, container, false);
        recyclerView= (RecyclerView) temp.findViewById(R.id.rcview);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());



        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(temp.getContext(), LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Log.d( msg1,Integer.toString(alist.size()));
        mAdapter = new MyAdapter(alist);

        recyclerView.setAdapter(mAdapter);
        Log.d( msg,Integer.toString(alist.size()));



    getdata(temp);



        double user[][] = {
                {10, 10, 0, 3, 0, 0, 4, 8, 0, 1},
                {10, 0, 9, 0, 7, 0, 8, 1, 0, 4},
                {9, 0, 0, 1, 0, 8, 8, 0, 6, 8},
                {2, 7, 1, 0, 6, 0, 10, 5, 6, 2},
                {0, 0, 0, 7, 0, 4, 3, 7, 0, 10},
                {7, 10, 2, 4, 7, 2, 0, 6, 1, 6},
                {0, 0, 0, 0, 0, 9, 7, 1, 0, 4},
                {5, 6, 0, 3, 0, 0, 6, 0, 5, 0},
                {1, 0, 7, 7, 0, 0, 9, 8, 0, 2},
                {0, 9, 10, 0, 7, 2, 0, 5, 5, 0}
        };
        double result[] = new double[10];

        //svd

        for (int i = 0; i < 10; i++) {
            double count = 0.0;
            for (int j = 0; j < 10; j++) {

                count += user[i][j];
            }
            double mean = count / 3;
            for (int j = 0; j < 10; j++) {
                user[i][j] = mean - user[i][j];
            }
        }
        //user
        //input

        int key = 4;
        for (int i = 0; i < 3; i++) {
            double res = 0.0;
            double u1mod = 0.0;
            double u2mod = 0.0;
            if (key != i) {

                for (int k = 0; k < 3; k++) {
                    res += user[key][k] * user[i][k];
                    u1mod += user[key][k] * user[key][k];
                    u2mod += user[i][k] * user[i][k];
                }
                u1mod = Math.sqrt(u1mod);
                u2mod = Math.sqrt(u2mod);
                result[i] = res / (u1mod * u2mod);
            }
        }

        //find mini
        double min = 3000;
        int ind = -1;
        for (int l = 0; l < 10; l++) {
            if (result[l] < min) {
                if (l != key) {
                    min = result[l];
                    ind = l;
                }
            }
        }
        return temp;
    }

    void getdata(View temp) {
//get list of exercise
        JsonArrayRequest ja = new JsonArrayRequest(Request.Method.POST,
                Constants.URL_EXE,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count < response.length()) {
                            try {
                                JSONObject jb = response.getJSONObject(count);
                                exercise exe = new exercise(jb.getString("name"), jb.getString("Description"),jb.getDouble("completed"));
                                alist.add(exe);
                                Log.d(msg, Integer.toString(alist.size()));
                                count++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", username);
                return params;
            }

        };;
        usersingleton.getInstance(temp.getContext()).addtorequest(ja);

    }



}




