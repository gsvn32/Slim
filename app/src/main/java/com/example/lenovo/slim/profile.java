package com.example.lenovo.slim;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;



/**
 * Created by LENOVO on 9/23/2018.
 */

public class profile extends Fragment{
    EditText Username, UserEmail,userlname,usergender,userage,userheight,userweight,userpass,userid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rv= inflater.inflate(R.layout.profile,container,false);
        if(!SharedPrefManager.getInstance(rv.getContext()).isLoggedIn()){

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new login_act())
                    .commit();
        }


        userid = (EditText)rv.findViewById(R.id.editUSERID2);
        Username= (EditText) rv.findViewById(R.id.editFNAME2);
        userlname= (EditText) rv.findViewById(R.id.editLNAME2);
        userage= (EditText) rv.findViewById(R.id.editAGE2);
        UserEmail= (EditText) rv.findViewById(R.id.editEMAIL2);
        userweight= (EditText) rv.findViewById(R.id.editWEIGHT2);
        userheight= (EditText) rv.findViewById(R.id.editHEIGHT2);
        usergender= (EditText) rv.findViewById(R.id.editGENDER2);
        userpass= (EditText) rv.findViewById(R.id.editPASSWD2);



        UserEmail.setText(SharedPrefManager.getInstance(rv.getContext()).getUserEmail());
        Username.setText(SharedPrefManager.getInstance(rv.getContext()).getUsername());
        userid.setText(SharedPrefManager.getInstance(rv.getContext()).getUserid());
        userlname.setText(SharedPrefManager.getInstance(rv.getContext()).getUserlname());

        userage.setText(String.valueOf(SharedPrefManager.getInstance(rv.getContext()).getUserage()));
        userheight.setText(String.valueOf(SharedPrefManager.getInstance(rv.getContext()).getUserheight()));
        userweight.setText(String.valueOf(SharedPrefManager.getInstance(rv.getContext()).getUserweight()));
        usergender.setText(SharedPrefManager.getInstance(rv.getContext()).getUsergender());
        userpass.setText(SharedPrefManager.getInstance(rv.getContext()).getUserpass());



        return rv;
    }
}












