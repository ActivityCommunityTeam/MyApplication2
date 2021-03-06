package com.example.administrator.activitycommunity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.activitycommunity.R;
import com.example.administrator.activitycommunity.model.Activitys;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */

public class FX_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Activitys> mDatas;
    private final LayoutInflater mLayoutInflater;
    private MyItemClickListener mItemClickListener;

    public FX_Adapter(Context mContext, List<Activitys> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mLayoutInflater =  LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.fx_recyclerview_list, parent, false);
        RecyclerView.ViewHolder viewHolder = new MyViewHoder(v,mItemClickListener);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHoder myViewHoder = (MyViewHoder) holder;
        String _imgUrl = mDatas.get(position).getImage_url();
        Picasso.with(mContext).load(_imgUrl).into(myViewHoder.activity_pic_img);
        myViewHoder.activity_address_tv.setText("地点："+mDatas.get(position).getSite());
        myViewHoder.activity_attended_tv.setText("已报名："+mDatas.get(position).getAttended());
//        myViewHoder.activity_compair_tv.setText(mDatas.get(position).getSite());
        myViewHoder.activity_price_tv.setText("￥"+mDatas.get(position).getPrice());
        int _sumPrice =mDatas.get(position).getPrice()*mDatas.get(position).getAttended();
//        myViewHoder.activity_sumPrice_tv.setText("总金额："+_sumPrice);
        myViewHoder.activity_time_tv.setText("时间："+mDatas.get(position).getBegin_time());
        myViewHoder.activity_title_tv.setText(mDatas.get(position).getActivity_title());
    }

    @Override
    public int getItemCount() {
        return mDatas==null?0:mDatas.size();
    }
    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public interface MyItemClickListener {
        public void onItemClick(View view,int postion);
    }

    static class MyViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView activity_pic_img;
        TextView  activity_title_tv;
        TextView  activity_time_tv;
        TextView  activity_address_tv;
//        TextView  activity_compair_tv;
//        TextView  activity_sumPrice_tv;
        TextView  activity_attended_tv;
        TextView  activity_price_tv;
        private MyItemClickListener mListener;

        public MyViewHoder(View view,MyItemClickListener listener) {
            super(view);
            activity_pic_img = (ImageView) view.findViewById(R.id.activity_pic_img);
            activity_title_tv = (TextView) view.findViewById(R.id.activity_title_tv);
            activity_time_tv = (TextView) view.findViewById(R.id.activity_time_tv);
            activity_address_tv = (TextView) view.findViewById(R.id.activity_address_tv);
//            activity_compair_tv = (TextView) view.findViewById(R.id.activity_compair_tv);
//            activity_sumPrice_tv = (TextView) view.findViewById(R.id.activity_sumPrice_tv);
            activity_attended_tv = (TextView) view.findViewById(R.id.activity_attended_tv);
            activity_price_tv = (TextView) view.findViewById(R.id.activity_price_tv);
            this.mListener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener != null){
                mListener.onItemClick(view,getPosition());
            }

        }
    }
}
