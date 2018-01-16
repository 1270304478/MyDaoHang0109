package che.bwei.com.mydaohang0109.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.activity.SearchActivity;
import che.bwei.com.mydaohang0109.adapter.FenLeiRightRecyAdapter;
import che.bwei.com.mydaohang0109.adapter.FenLeiRightRecyBigAdapter;
import che.bwei.com.mydaohang0109.adapter.FenleiListAdapter;
import che.bwei.com.mydaohang0109.bannerGilde.GlideImageLoader;
import che.bwei.com.mydaohang0109.bean.FenLeiLeftBean;
import che.bwei.com.mydaohang0109.bean.FenLeiRightBean;
import che.bwei.com.mydaohang0109.presenter.FenleiRightPresenter;
import che.bwei.com.mydaohang0109.presenter.MyFenleiListViewPresenter;
import che.bwei.com.mydaohang0109.view.FenleiListViewCallBack;
import che.bwei.com.mydaohang0109.view.FenleiRightViewCallBack;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2018/1/9 15:53
 */
public class Fragment02 extends Fragment implements FenleiListViewCallBack, FenleiRightViewCallBack {
    @BindView(R.id.list_view)
    ListView listView;
    Unbinder unbinder;

    @BindView(R.id.fenlei_right_recy)
    RecyclerView fenleiRightRecy;
    @BindView(R.id.fragment_banner)
    Banner fragmentBanner;
    @BindView(R.id.fenlei_sousuo)
    TextView fenleiSousuo;
    @BindView(R.id.saoyisao)
    ImageView saoyisao;
    private MyFenleiListViewPresenter myFenleiListViewPresenter;
    private FenleiListAdapter fenleiListAdapter;
    private FenleiRightPresenter rightPresenter;
    String cid = "0";
    private FenLeiRightRecyAdapter fenLeiRightRecyAdapter;
    private FenLeiRightRecyBigAdapter fenLeiRightRecyBigAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment02, container, false);
        unbinder = ButterKnife.bind(this, view);

        fenleiListAdapter = new FenleiListAdapter(getActivity());//左侧listview
        fenLeiRightRecyAdapter = new FenLeiRightRecyAdapter(getActivity());//右侧的小recyview
        fenLeiRightRecyBigAdapter = new FenLeiRightRecyBigAdapter(getActivity());//右侧的大recyview

        myFenleiListViewPresenter = new MyFenleiListViewPresenter(this);
        myFenleiListViewPresenter.getData();//左侧listview的请求数据

        fragmentBanner.setImageLoader(new GlideImageLoader());
        List<Integer> listLunbo = new ArrayList<>();
        listLunbo.add(R.drawable.fenlei_lunbo);
        listLunbo.add(R.drawable.fenlei_lunbo1);
        listLunbo.add(R.drawable.fenlei_lunbo2);
        listLunbo.add(R.drawable.fenlei_lunbo3);
        fragmentBanner.setImages(listLunbo);
        fragmentBanner.start();
        //右边的 presenter
        rightPresenter = new FenleiRightPresenter(this);
        //进入页面默认传cid为1 显示
        rightPresenter.getData(String.valueOf(cid));
        //右侧的大recy
        fenleiRightRecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    //重写的方法
    @Override
    public void success(final FenLeiLeftBean fenLeiLeftBean) {
        fenleiListAdapter.addData(fenLeiLeftBean.getData());
        listView.setAdapter(fenleiListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int cid = fenLeiLeftBean.getData().get(i).getCid();
                rightPresenter.getData(String.valueOf(cid));//右侧的请求
                // Toast.makeText(getActivity(),String.valueOf(cid),Toast.LENGTH_LONG).show();

            }
        });
    }

    //右边的回调
    @Override
    public void success(FenLeiRightBean fenLeiRightBean) {
        // System.out.println(fenLeiRightBean.getData().get(0).getName());
        //把右边的集合添加到适配器里.
        //fenLeiRightRecyAdapter.addData(fenLeiRightBean.getData());
        //fenleiRightRecy.setAdapter(fenLeiRightRecyAdapter);
        fenLeiRightRecyBigAdapter.addData(fenLeiRightBean.getData());
        fenleiRightRecy.setAdapter(fenLeiRightRecyBigAdapter);

    }

    @Override
    public void failure() {

    }

    @OnClick({R.id.saoyisao, R.id.fenlei_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.saoyisao:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.fenlei_sousuo:
                //点击搜索框 跳转到搜索页面
                Intent intent1 = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent1);
                break;
        }
    }





}
