package che.bwei.com.mydaohang0109.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.bean.FenLeiLeftBean;

/**
 * Created by Menglucywhh on 2017/12/4.
 */

public class FenleiListAdapter extends BaseAdapter{
    List<FenLeiLeftBean.DataBean> list;
    Context context;
    public FenleiListAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<FenLeiLeftBean.DataBean> data){
          if(list==null){
              list = new ArrayList<>();
          }
          list.addAll(data);
          notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view==null){
            view = View.inflate(context, R.layout.list_fenlei_item,null);

            holder = new ViewHolder();
            holder.textView = (TextView) view.findViewById(R.id.fenlei_text);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(list.get(i).getName());



        return view;
    }

    class ViewHolder{
       TextView textView;
    }
}
