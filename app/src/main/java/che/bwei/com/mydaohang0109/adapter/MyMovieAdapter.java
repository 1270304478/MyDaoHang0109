package che.bwei.com.mydaohang0109.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.bean.MovieBean;


/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/13 20:20
 */
public class MyMovieAdapter extends BaseAdapter {
    List<MovieBean.RetBean.ListBean> list;
    Context context;
    public MyMovieAdapter( List<MovieBean.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
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
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.list_item_movie, null);
            holder = new ViewHolder();

            holder.image_view_movice = view.findViewById(R.id.image_view_movice);
            holder.text_view_movice = view.findViewById(R.id.text_view_movice);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
          holder.image_view_movice.setImageURI(Uri.parse(list.get(i).getPic()));
          holder.text_view_movice.setText(list.get(i).getTitle());
        return view;
    }

    public class ViewHolder {
        ImageView image_view_movice;
        TextView text_view_movice;
    }
}
