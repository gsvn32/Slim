package com.example.lenovo.slim;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 10/22/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final String msg="myadapter";
    private ArrayList<exercise> list;
    private String tag="In adapter";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, des,per;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.EXERCISENAME1);
            des = (TextView) view.findViewById(R.id.description);
            per=(TextView)view.findViewById(R.id.perc);

        }
    }


    public MyAdapter(ArrayList<exercise> list) {
        this.list = list;
        Log.d( msg,Integer.toString(list.size()));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        exercise movie = list.get(position);
        holder.title.setText(movie.getName());
        Log.d(tag,movie.getName());
        holder.des.setText(movie.getDes());
        holder.per.setText(Double.toString(movie.getPer()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}