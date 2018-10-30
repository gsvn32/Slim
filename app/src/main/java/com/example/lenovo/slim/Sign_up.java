package com.example.lenovo.slim;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NIKHIL on 9/23/2018.
 */

public class Sign_up extends AppCompatActivity implements View.OnClickListener{

    EditText name,lname,gender,age,height,weight,email,pass,userid;
    Button submit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        name=(EditText)findViewById(R.id.editFNAME2);
        lname=(EditText)findViewById(R.id.editLNAME2);
        gender=(EditText)findViewById(R.id.editGENDER2);
        age=(EditText)findViewById(R.id.editAGE2);
        userid=(EditText)findViewById(R.id.editUSERID2);
        height=(EditText)findViewById(R.id.editHEIGHT2);
        weight=(EditText)findViewById(R.id.editWEIGHT2);
        email=(EditText)findViewById(R.id.editEMAIL2);
        pass=(EditText)findViewById(R.id.editPASSWD2);
        submit=(Button)findViewById(R.id.SUBMIT);
        submit.setOnClickListener(this);


    }
    public void registerUser(){

        StringRequest sr=new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsobj= null;
                        try {
                            jsobj = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            Toast.makeText(getApplicationContext(),jsobj.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }){
          protected Map<String,String> getParams() throws AuthFailureError{
              Map<String,String> params=new HashMap<>();

              params.put("userid",userid.getText().toString().trim());
              params.put("name",name.getText().toString().trim());
              params.put("lname",lname.getText().toString().trim());

              params.put("age",age.getText().toString().trim());
              params.put("height",height.getText().toString().trim());
              params.put("weight",weight.getText().toString().trim());
              params.put("password",pass.getText().toString().trim());

              params.put("email",email.getText().toString().trim());
              params.put("gender",gender.getText().toString().trim());
              return  params;
          }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(sr);
    }

    @Override
    public void onClick(View v) {
        if(userid.getText().toString().trim().length()==0 || name.getText().toString().trim().length()==0 || lname.getText().toString().trim().length()==0|| gender .getText().toString().trim().length()==0 ||
        email.getText().toString().trim().length()==0 || pass.getText().toString().trim().length()==0 || height.getText().toString().trim().length()==0
                || weight.getText().toString().trim().length()==0 || age.getText().toString().trim().length()==0){
            Toast.makeText(getApplicationContext(),"please provide all details", Toast.LENGTH_SHORT).show();
        }
        else
        registerUser();



    }
}
