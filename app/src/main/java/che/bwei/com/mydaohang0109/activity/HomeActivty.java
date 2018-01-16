package che.bwei.com.mydaohang0109.activity;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.fragment.Fragment01;
import che.bwei.com.mydaohang0109.fragment.Fragment02;
import che.bwei.com.mydaohang0109.fragment.Fragment03;
import che.bwei.com.mydaohang0109.fragment.Fragment04;
import che.bwei.com.mydaohang0109.fragment.Fragment05;

public class HomeActivty extends FragmentActivity {
    private long exitTime = 0;
    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activty);
        ButterKnife.bind(this);

      bottomTabBar.init(getSupportFragmentManager())
              .setImgSize(50,50)
              .setFontSize(13)
              .setTabPadding(4,3,4)
              .setChangeColor(Color.RED,Color.DKGRAY)
              .addTabItem("首页",R.drawable.shou_ye02, Fragment01.class)
              .addTabItem("分类",R.drawable.fen_lei, Fragment02.class)
              .addTabItem("发现",R.drawable.fa_xian, Fragment03.class)
              .addTabItem("购物车",R.drawable.gouwu_che, Fragment04.class)
              .addTabItem("我的",R.drawable.wo_de, Fragment05.class)
              .isShowDivider(false)
              .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                  @Override
                  public void onTabChange(int position, String name) {
                  }
              });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 1500) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
