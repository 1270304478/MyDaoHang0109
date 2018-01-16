package che.bwei.com.mydaohang0109.model;



import che.bwei.com.mydaohang0109.bean.FenLeiLeftBean;
import che.bwei.com.mydaohang0109.retrfit.IGetDataService;
import che.bwei.com.mydaohang0109.view.FenleiListModelCallBack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MyFenleiListViewModel {

    public void getData(final FenleiListModelCallBack fenleiListModelCallBack) {
//https://www.zhaoapi.cn/product/getCatagory
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IGetDataService service = retrofit.create(IGetDataService.class);

        service.getFenLei().enqueue(new Callback<FenLeiLeftBean>() {
            @Override
            public void onResponse(Call<FenLeiLeftBean> call, Response<FenLeiLeftBean> response) {
                FenLeiLeftBean fenLeiLeftBean = response.body();
                fenleiListModelCallBack.success(fenLeiLeftBean);
            }

            @Override
            public void onFailure(Call<FenLeiLeftBean> call, Throwable t) {
                fenleiListModelCallBack.failure();
            }
        });
    }
}
