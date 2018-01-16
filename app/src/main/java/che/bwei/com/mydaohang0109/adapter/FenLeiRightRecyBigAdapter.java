package che.bwei.com.mydaohang0109.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.bean.FenLeiRightBean;

/**
 * Created by Menglucywhh on 2017/12/6.
 */

public class FenLeiRightRecyBigAdapter extends RecyclerView.Adapter<FenLeiRightRecyBigAdapter.MyViewHolder> {

    Context context;
    List<FenLeiRightBean.DataBean> listGridDa;
    private FenLeiRightRecyAdapter fenLeiRightRecyAdapter;

    public FenLeiRightRecyBigAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<FenLeiRightBean.DataBean> data) {
        if(listGridDa==null){
            listGridDa = new ArrayList<>();
        }
        listGridDa.clear();
        listGridDa.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.fenlei_right_recybig,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.fenlei_right_recybig_text.setText(listGridDa.get(position).getName());
        //给下面的recyclerview设置适配器
        fenLeiRightRecyAdapter = new FenLeiRightRecyAdapter(context);
        fenLeiRightRecyAdapter.addData(listGridDa.get(position).getList());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        holder.fenlei_right_recybig_recyview.setLayoutManager(gridLayoutManager);
        holder.fenlei_right_recybig_recyview.setAdapter(fenLeiRightRecyAdapter);


    }

    @Override
    public int getItemCount() {
        return listGridDa==null?0:listGridDa.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView fenlei_right_recybig_text;
        private final RecyclerView fenlei_right_recybig_recyview;

        public MyViewHolder(View itemView) {
            super(itemView);
             fenlei_right_recybig_text = (TextView) itemView.findViewById(R.id.fenlei_right_recybig_text);
            fenlei_right_recybig_recyview = (RecyclerView) itemView.findViewById(R.id.fenlei_right_recybig_recyview);
        }
    }
}
