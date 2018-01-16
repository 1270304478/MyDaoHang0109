package che.bwei.com.mydaohang0109.model;


import java.util.HashMap;
import java.util.Map;

import che.bwei.com.mydaohang0109.bean.SearchBean;
import che.bwei.com.mydaohang0109.retrfit.IGetDataService;
import che.bwei.com.mydaohang0109.view.SearchModelCallBack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class SearchModel {


    public void getData(String editSearch, final SearchModelCallBack searchModelCallBack) {
//https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IGetDataService service = retrofit.create(IGetDataService.class);

        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("keywords",editSearch);
        map.put("page","1");
        service.search(map).enqueue(new Callback<SearchBean>() {
            @Override
            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                SearchBean searchBean = response.body();
                searchModelCallBack.success(searchBean);
            }

            @Override
            public void onFailure(Call<SearchBean> call, Throwable t) {
                searchModelCallBack.failure();
            }
        });
    }
}
