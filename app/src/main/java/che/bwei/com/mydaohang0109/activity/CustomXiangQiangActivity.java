package che.bwei.com.mydaohang0109.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.adapter.ViewPagerAdapter;
import che.bwei.com.mydaohang0109.bean.AddCartBean;
import che.bwei.com.mydaohang0109.presenter.AddCartPresenter;
import che.bwei.com.mydaohang0109.view.AddCartViewCallBack;

public class CustomXiangQiangActivity extends Activity implements AddCartViewCallBack {

    @BindView(R.id.custom_fanhui)
    ImageView customFanhui;
    @BindView(R.id.custom_xq_viewpager)
    ViewPager customXqViewpager;
    @BindView(R.id.custom_xq_title)
    TextView customXqTitle;
    @BindView(R.id.custom_xq_bargin_price)
    TextView customXqBarginPrice;
    @BindView(R.id.custom_xq_price)
    TextView customXqPrice;
    @BindView(R.id.jiagou_btn)
    TextView jiagouBtn;
    private AddCartPresenter addCartPresenter;
    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_xiang_qiang);
        ButterKnife.bind(this);
        //https://www.zhaoapi.cn/product/addCart
        //添加购物车

        //拿到传来的参数
        Intent intent = getIntent();
        //images,pid,bargainPrice,title,price
        pid = intent.getStringExtra("pid");
        String images = intent.getStringExtra("images");
        String bargainPrice = intent.getStringExtra("bargainPrice");
        String title = intent.getStringExtra("title");
        String price = intent.getStringExtra("price");


        //原价设置删除线
        customXqPrice.setText("¥"+price+"");
        customXqTitle.setText(title+"");
        customXqBarginPrice.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        customXqBarginPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线（删除线）
        customXqBarginPrice.getPaint().setAntiAlias(true);// 抗锯齿
        customXqBarginPrice.setText("¥"+bargainPrice+"");

        List<String> listImage = new ArrayList<>();

        //图片的集合
        if(images.contains("|")){
            //如果需要拆分
            String[] split = images.split("\\|");
            for (int i=0;i<split.length;i++){
                listImage.add(split[0]);
            }
        }else{
            listImage.add(images);
        }

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPagerAdapter.addData(listImage);
        customXqViewpager.setAdapter(viewPagerAdapter);

        addCartPresenter = new AddCartPresenter(this);

    }

    @OnClick({R.id.custom_fanhui, R.id.custom_xq_viewpager, R.id.custom_xq_title, R.id.custom_xq_bargin_price, R.id.custom_xq_price, R.id.jiagou_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.custom_fanhui:
                finish();
                break;

            case R.id.jiagou_btn://点击加入购物车,动态添加商品根据pid,
                // https://www.zhaoapi.cn/product/addCart
                //"uid": 1650,
                // "token": "2FC3EF31EA25696D2715A971ADE38DE1",
                addCartPresenter.getData(pid);
                break;
        }
    }

    @Override
    public void success(AddCartBean addCartBean) {

        Toast.makeText(this,""+addCartBean.getMsg(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void failure() {

    }
}
