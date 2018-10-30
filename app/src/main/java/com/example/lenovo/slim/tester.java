package com.example.lenovo.slim;

import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LENOVO on 10/19/2018.
 */

public class tester extends Fragment implements View.OnClickListener{
    private Context mContext;


    private CoordinatorLayout mCLayout;
    private Button mButtonDo;
    private TextView mTextView;
    private String mJSONURLString = "http://192.168.11.1/slim/v1/userrating.php";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View temp = inflater.inflate(R.layout.test1, container, false);

        // Get the application context
        mContext =temp.getContext();


        // Get the widget reference from XML layout
        mCLayout = (CoordinatorLayout) temp.findViewById(R.id.coordinator_layout);
        mButtonDo = (Button) temp.findViewById(R.id.btn_do);
        mTextView = (TextView)temp.findViewById(R.id.tv);

        // Set a click listener for button widget
        mButtonDo.setOnClickListener(this);

    return  temp;
    }
    @Override
    public void onClick(View view) {
        // Empty the TextView

        mTextView.setText("");

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject student = response.getJSONObject(i);

                                // Get the current student (json object) data
                                String firstName = student.getString("name");
                                String lastName = student.getString("Description");


                                // Display the formatted json data in text view
                                mTextView.append(firstName +"\n" + lastName);
                                mTextView.append("\n\n");
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Snackbar.make(
                                mCLayout,
                                "Error...",
                                Snackbar.LENGTH_LONG
                        ).show();
                    }
                }
        );

        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);
    }

}
