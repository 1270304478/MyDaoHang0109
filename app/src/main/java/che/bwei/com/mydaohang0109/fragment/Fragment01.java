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
import android.widget.ImageView;

import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.activity.SearchActivity;
import che.bwei.com.mydaohang0109.adapter.ShouyeRecyclerViewAdapter;
import che.bwei.com.mydaohang0109.bean.ShouYeLunBoBean;
import che.bwei.com.mydaohang0109.presenter.MyPresenter1;
import che.bwei.com.mydaohang0109.view.ViewCallBack1;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2018/1/9 15:53
 */
public class Fragment01 extends Fragment implements ViewCallBack1 {

    @BindView(R.id.shouye_recyclerView)
    RecyclerView shouyeRecyclerView;
    Unbinder unbinder;
    private ShouyeRecyclerViewAdapter shouyeRecyViewAdapter;
    private MyPresenter1 myPresenter1;
    private LinearLayoutManager linearLayoutManager;
    private ShouyeRecyclerViewAdapter shouyeRecyclerViewAdapter;
    private ImageView saoyisao;
    private RecyclerView shouye_recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shouye2, container, false);
        unbinder = ButterKnife.bind(this, view);
        saoyisao = (ImageView) view.findViewById(R.id.saoyisao);
        shouye_recyclerView = (RecyclerView) view.findViewById(R.id.shouye_recyclerView);
        shouyeRecyViewAdapter = new ShouyeRecyclerViewAdapter(getActivity(),getChildFragmentManager());
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        shouyeRecyclerView.setLayoutManager(linearLayoutManager);
        myPresenter1 = new MyPresenter1(this);
        /**
         * 扫二维码
         * */
       /* saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,0);
            }
        });*/
        return view;
    }
   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == CaptureActivity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");

        } else if (resultCode == CaptureActivity.RESULT_CANCELED) {
        }
    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myPresenter1.getLunBo();
    }

    @Override
    public void success(ShouYeLunBoBean shouYeLunBoBean) {
        List<ShouYeLunBoBean.DataBean> listIMage = shouYeLunBoBean.getData();

        shouyeRecyViewAdapter.addTuijian(shouYeLunBoBean.getTuijian());
        shouyeRecyViewAdapter.addMiaosha(shouYeLunBoBean.getMiaosha());

        //将集合传给适配器
        shouyeRecyViewAdapter.addLunbo(listIMage);
        shouyeRecyclerView.setAdapter(shouyeRecyViewAdapter);
    }
    @Override
    public void failure() {
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.shouye_sousuo)
    public void onViewClicked() {
        //点击搜索框 跳转到搜索页面
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);

    }
}
