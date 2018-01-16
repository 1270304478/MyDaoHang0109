package che.bwei.com.mydaohang0109.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;

import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.adapter.SelectCartAdapter;
import che.bwei.com.mydaohang0109.bean.SelectCartBean;
import che.bwei.com.mydaohang0109.presenter.SelectCartPresenter;
import che.bwei.com.mydaohang0109.view.SelectCartViewCallBack;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2018/1/9 16:00
 */
public class Fragment04 extends Fragment implements SelectCartViewCallBack {
    private RecyclerView recyclerView;
    private TextView total_price;
    private TextView total_num;
    private CheckBox quanxuan;
    private SelectCartPresenter selectCartPresenter;
    private SelectCartAdapter selectCartAdapter;
    private SpringView springView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment04,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_View);
        total_price = (TextView) view.findViewById(R.id.total_price);
        total_num = (TextView) view.findViewById(R.id.total_num);
        quanxuan = (CheckBox) view.findViewById(R.id.quanxuan);
        springView = (SpringView) view.findViewById(R.id.springview);

        quanxuan.setTag(1);//1为不选中
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        selectCartAdapter = new SelectCartAdapter(getActivity());
        selectCartPresenter = new SelectCartPresenter(this);
        //调用presenter里面的请求数据的方法
        selectCartPresenter.getData();
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(selectCartAdapter);
        selectCartAdapter.notifyDataSetChanged();

        //设置上拉下拉
        springView.setHeader(new DefaultFooter(getActivity()));
        springView.setFooter(new DefaultFooter(getActivity()));

        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                selectCartPresenter.getData();
                springView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                selectCartPresenter.getData();
                springView.onFinishFreshAndLoad();
            }
        });

        //调用recyAdapter里面的接口,设置 全选按钮 总价 总数量
        selectCartAdapter.setUpdateListener(new SelectCartAdapter.UpdateListener() {
            @Override
            public void setTotal(String total, String num, boolean allCheck) {
                //设置ui的改变
                total_num.setText("共"+num+"件商品");//总数量
                total_price.setText("总价 :¥"+total+"元");//总价
                if(allCheck){
                    quanxuan.setTag(2);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_selected);
                }else{
                    quanxuan.setTag(1);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_unselected);
                }
                quanxuan.setChecked(allCheck);
            }
        });

        //这里只做ui更改, 点击全选按钮,,调到adapter里面操作
        quanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用adapter里面的方法 ,,把当前quanxuan状态传递过去

                int tag = (int) quanxuan.getTag();
                if(tag==1){
                    quanxuan.setTag(2);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_selected);
                }else{
                    quanxuan.setTag(1);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_unselected);
                }

                selectCartAdapter.quanXuan(quanxuan.isChecked());
            }
        });

        return view;
    }


    @Override
    public void success(SelectCartBean selectCartBean) {

        //将数据传给适配器
        if(selectCartBean!=null) {
            selectCartAdapter.add(selectCartBean);
            selectCartAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failure() {

    }

    @Override
    public void onResume() {
        super.onResume();
        selectCartPresenter.getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        selectCartPresenter.detach();
    }
}
