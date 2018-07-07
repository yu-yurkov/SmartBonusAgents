package com.example.hp.smartbonusagents.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hp.smartbonusagents.R;
import com.example.hp.smartbonusagents.model.Products;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Products> mData;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Products> mData) {
        this.mContext = mContext;
        this.mData = mData;
        // Options for Glide
        this.option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.products_row_item, parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_price.setText(mData.get(position).getPrice());

        // загрузка изображения
        Glide.with(mContext).load(mData.get(position).getPhoto()).apply(option).into(holder.img_photo);
    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }


    // MyViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_price;
        ImageView img_photo;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.name);
            tv_price = itemView.findViewById(R.id.price);
            img_photo = itemView.findViewById(R.id.photo);

        }
    }
}
