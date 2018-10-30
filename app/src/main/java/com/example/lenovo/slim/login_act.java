package com.example.lenovo.slim;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.lenovo.slim.R.id.sup;

/**
 * Created by NIKHIL on 9/13/2018.
 */

public class login_act extends Fragment implements View.OnClickListener {
    Button sign, login;
    EditText usern, pass;
    private ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rv = inflater.inflate(R.layout.login, container, false);
        sign = (Button) rv.findViewById(R.id.sup);
        usern = (EditText) rv.findViewById(R.id.editText);
        pass = (EditText) rv.findViewById(R.id.editText2);
        login = (Button) rv.findViewById(R.id.login2);
        login.setOnClickListener(this);
        sign.setOnClickListener(this);
        return rv;

    }

    @Override
    public void onClick(final View v) {

        if (v.getId() == R.id.login2) {
            final String username = usern.getText().toString().trim();
            final String password = pass.getText().toString().trim();
progressDialog=new ProgressDialog(v.getContext());
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Constants.URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject obj = new JSONObject(response);
                                if (!obj.getBoolean("error")) {
                                    SharedPrefManager.getInstance(v.getContext())
                                            .userLogin(
                                                    obj.getString("user_id"),
                                                    obj.getString("name"),
                                                    obj.getString("lname"),
                                                    obj.getString("email"),
                                                    obj.getString("password"),
                                                    obj.getString("gender"),
                                                    obj.getInt("age"),

                                                    obj.getInt("weight"),
                                                    obj.getInt("height")
                                            );
                                    getFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.fragment_container, new profile())
                                            .commit();

                                } else {
                                    Toast.makeText(
                                            v.getContext(),
                                            obj.getString("message"),
                                            Toast.LENGTH_LONG
                                    ).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();

                            Toast.makeText(
                                    v.getContext(),
                                    error.getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }

            };

            RequestHandler.getInstance(v.getContext()).addToRequestQueue(stringRequest);


        }
        if (v.getId() == R.id.sup) {
            Intent intent = new Intent(v.getContext(), Sign_up.class);
            startActivity(intent);
        }

    }
}

