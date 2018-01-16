package che.bwei.com.mydaohang0109.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.sax.SAXTransformerFactory;

import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.bean.FenLeiRightBean;

/**
 * Created by Menglucywhh on 2017/12/6.
 */

public class FenLeiRightRecyAdapter extends RecyclerView.Adapter<FenLeiRightRecyAdapter.MyViewHolder> {

    Context context;
    List<FenLeiRightBean.DataBean.ListBean> listGridDa;
    public FenLeiRightRecyAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<FenLeiRightBean.DataBean.ListBean> data) {
        if(listGridDa==null){
            listGridDa = new ArrayList<>();
        }
        listGridDa.clear();
        listGridDa.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = View.inflate(context, R.layout.fenlei_right_recyitem,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.fenlei_right_recy_image.setImageURI(listGridDa.get(position).getIcon());
        holder.fenlei_right_recy_text.setText(listGridDa.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return listGridDa==null?0:listGridDa.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView fenlei_right_recy_image;
        private final TextView fenlei_right_recy_text;

        public MyViewHolder(View itemView) {
            super(itemView);
            fenlei_right_recy_image = (SimpleDraweeView) itemView.findViewById(R.id.fenlei_right_recy_image);
            fenlei_right_recy_text = (TextView) itemView.findViewById(R.id.fenlei_right_recy_text);
        }
    }
}
