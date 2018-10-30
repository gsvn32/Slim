package com.example.lenovo.slim;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LENOVO on 10/18/2018.
 */

public class RecAdapt extends RecyclerView.Adapter<RecAdapt.myview> {
ArrayList<exercise> alist=new ArrayList<>();
    public  RecAdapt(ArrayList<exercise> alist){
        this.alist=alist;
    }
    @Override
    public myview onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        myview my=new myview(view);
        return my;
    }

    @Override
    public void onBindViewHolder(RecAdapt.myview holder, int position) {
        holder.name.setText(alist.get(position).getName());
        holder.des.setText(alist.get(position).getDes());

    }



    @Override
    public int getItemCount() {
        return alist.size();
    }
    public static class myview extends RecyclerView.ViewHolder{
TextView name,des;
        public myview(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.EXERCISENAME1);
            des=(TextView)itemView.findViewById(R.id.description);
        }
    }
}
