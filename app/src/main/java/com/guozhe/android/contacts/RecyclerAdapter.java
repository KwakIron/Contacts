package com.guozhe.android.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by guozhe on 2017. 9. 26..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    List<Data> datas;

    public RecyclerAdapter(List<Data> datas){
        this.datas = datas;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Data data = datas.get(position);
        holder.id.setText(data.getId()+"");
        holder.name.setText(data.getName());
        holder.tel.setText(data.getTel());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView id,name,tel;
        public Holder(View itemView) {
            super(itemView);
            id = (TextView)itemView.findViewById(R.id.id);
            name = (TextView)itemView.findViewById(R.id.name);
            tel = (TextView)itemView.findViewById(R.id.tel);
        }
    }
}
