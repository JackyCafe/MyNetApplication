package com.ian.myapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class NetAdapter extends RecyclerView.Adapter <NetAdapter.VH>{

    private List<SiteName> mData;


    public  NetAdapter(){

    }

    public void setmData(List<SiteName> data){
        mData = data;
        Log.v("NetAdapter",mData.size()+"");
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myitem2, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.tvName.setText(mData.get(position).getName());
        holder.tvAddress.setText(mData.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class VH extends RecyclerView.ViewHolder{
        TextView tvName,tvAddress;
        public VH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);

        }
    }


}
