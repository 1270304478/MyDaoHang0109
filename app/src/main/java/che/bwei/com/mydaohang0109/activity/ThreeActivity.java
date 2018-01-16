package che.bwei.com.mydaohang0109.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import android.widget.LinearLayout.LayoutParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.adapter.MyPagerAdapter;

public class ThreeActivity extends Activity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.btn_dao_hang)
    Button btnDaoHang;
    private List<Integer> list;
    private List<ImageView> imageList;//小圆点的集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        ButterKnife.bind(this);
        //图片的数据
        list = new ArrayList<Integer>();
        list.add(R.drawable.a);
        list.add(R.drawable.b);
        list.add(R.drawable.c);
        list.add(R.drawable.d);
        list.add(R.drawable.e);
//初始化小圆点的方法
        initDoc();
        MyPagerAdapter adapter = new MyPagerAdapter(list, ThreeActivity.this);
        viewPager.setAdapter(adapter);
        //设置viewpager的监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //小圆点与viewPager联动
                for (int i = 0; i < list.size(); i++) {
                    if (i == position) {//当前位置的小圆点应该是红色的
                        imageList.get(i).setImageResource(R.drawable.shape_02);
                    }else {
                        imageList.get(i).setImageResource(R.drawable.shape_01);

                    }
                }

                //button的显示与隐藏
                if (position == list.size()-1) {
                    btnDaoHang.setVisibility(View.VISIBLE);
                }else {

                    btnDaoHang.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btnDaoHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThreeActivity.this,HomeActivty.class));
                finish();
              }
        });
    }

    /**
     * 初始化小圆点的方法
     * <p>
     * 1.imageView...添加到layout
     * 2.创建一个集合记录这些小圆点的图片
     */
    private void initDoc() {
        imageList = new ArrayList<ImageView>();

        //for循环创建小圆点,,,加到布局,,,加到集合.....在循环之前先清空一下集合和布局
        linear.removeAllViews();
        imageList.clear();

        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(ThreeActivity.this);
            if (i == 0) {//显示的是第一张图片的时候
                imageView.setImageResource(R.drawable.shape_02);//选中,,,红色
            } else {
                imageView.setImageResource(R.drawable.shape_01);
            }

            //添加...LayoutParams布局的参数...linearLayout下面的LayoutParams
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //圆点之间的间距
            params.setMargins(5, 0, 5, 0);
            //添加到布局
            linear.addView(imageView, params);
            //添加到集合
            imageList.add(imageView);

        }

    }
}
