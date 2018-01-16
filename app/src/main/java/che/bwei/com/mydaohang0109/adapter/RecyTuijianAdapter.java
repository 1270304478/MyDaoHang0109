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
import che.bwei.com.mydaohang0109.bean.ShouYeLunBoBean;

/**
 * Created by Menglucywhh on 2017/11/27.
 */
public class RecyTuijianAdapter extends RecyclerView.Adapter<RecyTuijianAdapter.TuijianViewHolder>{

    List<ShouYeLunBoBean.TuijianBean> listDa;
    Context context;
    public RecyTuijianAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<ShouYeLunBoBean.TuijianBean> list) {
        if(listDa==null){
            listDa = new ArrayList<>();
        }
        listDa.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public TuijianViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shouye_recy_tuijian_item,null);
        TuijianViewHolder tuijianViewHolder = new TuijianViewHolder(view);
        return tuijianViewHolder;
    }

    @Override
    public void onBindViewHolder(TuijianViewHolder holder, final int position) {
        if(listDa.size()>0) {
            if(listDa.get(0).getList().get(position).getImages().contains("|")) {
                String[] split = listDa.get(0).getList().get(position).getImages().split("\\|");
                holder.tuijian_image.setImageURI(split[0]);
                holder.tuijian_text.setText(listDa.get(0).getList().get(position).getTitle());

             }else {
                holder.tuijian_image.setImageURI(listDa.get(0).getList().get(position).getImages());
                holder.tuijian_text.setText(listDa.get(0).getList().get(position).getTitle());
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(context, XiangQingActivity.class);
                intent.putExtra("detailUrl",listDa.get(0).getList().get(position).getDetailUrl());
                context.startActivity(intent);
*/
                 Intent intent = new Intent(context, CustomXiangQiangActivity.class);
                intent.putExtra("pid",listDa.get(0).getList().get(position).getPid()+"");
                intent.putExtra("images",listDa.get(0).getList().get(position).getImages());
                intent.putExtra("bargainPrice",listDa.get(0).getList().get(position).getBargainPrice()+"");
                intent.putExtra("title",listDa.get(0).getList().get(position).getTitle());
                intent.putExtra("price",listDa.get(0).getList().get(position).getPrice()+"");

                 context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listDa==null?0:listDa.get(0).getList().size();
    }


    public static class TuijianViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView tuijian_image;
        private final TextView tuijian_text;

        public TuijianViewHolder(View itemView) {
            super(itemView);
            tuijian_image = (SimpleDraweeView) itemView.findViewById(R.id.tuijian_image);
            tuijian_text = (TextView) itemView.findViewById(R.id.tuijian_text);
        }
    }
}
