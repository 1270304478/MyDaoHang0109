package che.bwei.com.mydaohang0109.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.adapter.Search2GridAdapter;
import che.bwei.com.mydaohang0109.adapter.SearchAdapter;
import che.bwei.com.mydaohang0109.bean.SearchBean;
import che.bwei.com.mydaohang0109.presenter.SearchPresenter;
import che.bwei.com.mydaohang0109.view.SearchViewCallBack;


public class SearchActivity extends Activity implements SearchViewCallBack {

    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_text)
    TextView searchText;
    @BindView(R.id.search_qiehuan)
    ImageView searchQiehuan;
    @BindView(R.id.sousuo_recyview)
    RecyclerView sousuoRecyview;
    private SearchPresenter searchPresenter;
    private SearchAdapter searchAdapter;
    private Search2GridAdapter search2GridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        //https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1
        searchPresenter = new SearchPresenter(this);

        sousuoRecyview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        searchAdapter = new SearchAdapter(this);
        search2GridAdapter = new Search2GridAdapter(this);
    }

    @OnClick({R.id.fanhui, R.id.search_text, R.id.search_qiehuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                //点击返回结束当前页面
                finish();
                break;
            case R.id.search_text:
                //点击搜索按钮
                searchPresenter.getData(searchEdit.getText().toString());
                break;
            case R.id.search_qiehuan:
                //点击九宫格和linear'切换按钮
                int tag = (int) searchQiehuan.getTag();
                if (tag == 1) {
                    //如果当前是linar布局,就改成grid
                    //searchPresenter.getData(searchEdit.getText().toString());
                    sousuoRecyview.setLayoutManager(new GridLayoutManager(this, 2));
                    sousuoRecyview.setAdapter(search2GridAdapter);
                    //搜索以后 设置 切换布局的图片 显示
                    searchQiehuan.setVisibility(View.VISIBLE);
                    searchQiehuan.setImageResource(R.drawable.grid_icon);
                    searchQiehuan.setTag(2);//1为linear布局
                } else {
                    sousuoRecyview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                    sousuoRecyview.setAdapter(searchAdapter);
                    //搜索以后 设置 切换布局的图片 显示
                    searchQiehuan.setVisibility(View.VISIBLE);
                    searchQiehuan.setImageResource(R.drawable.linear_icon);
                    searchQiehuan.setTag(1);//1为linear布局
                }
                break;
        }
    }
    @Override
    public void success(SearchBean searchBean) {
        if (searchBean != null) {
            // System.out.println(searchBean.getData().get(0).getBargainPrice());
            searchAdapter.addData(searchBean.getData());
            search2GridAdapter.addData(searchBean.getData());
            sousuoRecyview.setAdapter(searchAdapter);
            //搜索以后 设置 切换布局的图片 显示
            searchQiehuan.setVisibility(View.VISIBLE);
            searchQiehuan.setImageResource(R.drawable.linear_icon);
            searchQiehuan.setTag(1);//1为linear布局
        }
    }
    @Override
    public void empty() {
        searchQiehuan.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
        searchAdapter.setListClear();
    }

    @Override
    public void falseEdit() {
        searchQiehuan.setVisibility(View.INVISIBLE);
        searchAdapter.setListClear();
        Toast.makeText(this, "请输入手机或笔记本", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure() {

    }
}
