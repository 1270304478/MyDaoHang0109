package che.bwei.com.mydaohang0109.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.activity.CustomXiangQiangActivity;
import che.bwei.com.mydaohang0109.bean.SearchBean;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{

    List<SearchBean.DataBean> listDa;
    Context context;
    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setListClear(){
        if(listDa!=null){

        listDa.clear();
        notifyDataSetChanged();
        }
    }
    public void addData(List<SearchBean.DataBean> list) {
        if(listDa==null){
            listDa = new ArrayList<>();
        }
        listDa.clear();
        listDa.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.search_item,null);
        SearchViewHolder tuijianViewHolder = new SearchViewHolder(view);
        return tuijianViewHolder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, final int position) {
        if(listDa.size()>0) {
            if(listDa.get(position).getImages().contains("|")) {
                String[] split = listDa.get(position).getImages().split("\\|");
                holder.search_image.setImageURI(split[0]);
                holder.search_text.setText(listDa.get(position).getTitle());

            }else {
                holder.search_image.setImageURI(listDa.get(position).getImages());
                holder.search_text.setText(listDa.get(position).getTitle());

            }
        }

        //条目的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

/*
               //将url传过去 访问详情页面
                Intent intent = new Intent(context, XiangQingActivity.class);
                intent.putExtra("detailUrl",listDa.get(position).getDetailUrl());
                context.startActivity(intent);*/

                Intent intent = new Intent(context, CustomXiangQiangActivity.class);
                intent.putExtra("pid",listDa.get(position).getPid()+"");
                intent.putExtra("images",listDa.get(position).getImages());
                intent.putExtra("bargainPrice",listDa.get(position).getBargainPrice()+"");
                intent.putExtra("title",listDa.get(position).getTitle());
                intent.putExtra("price",listDa.get(position).getPrice()+"");

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listDa==null?0:listDa.size();
    }


    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView search_image;
        private final TextView search_text;

        public SearchViewHolder(View itemView) {
            super(itemView);
            search_image = (SimpleDraweeView) itemView.findViewById(R.id.search_image);
            search_text = (TextView) itemView.findViewById(R.id.search_text);
        }
    }
}
