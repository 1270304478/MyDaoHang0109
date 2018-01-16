package che.bwei.com.mydaohang0109.model;



import java.util.HashMap;
import java.util.Map;

import che.bwei.com.mydaohang0109.bean.SelectCartBean;
import che.bwei.com.mydaohang0109.retrfit.IGetDataService;
import che.bwei.com.mydaohang0109.view.SelectCartModelCallBack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Menglucywhh on 2017/12/9.
 */

public class SelectCartModel {

    public void getData(final SelectCartModelCallBack selectCartModelCallBack) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IGetDataService service = retrofit.create(IGetDataService.class);

        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid","1650");
        map.put("token","2FC3EF31EA25696D2715A971ADE38DE1");

        service.selectCart(map).enqueue(new Callback<SelectCartBean>() {
            @Override
            public void onResponse(Call<SelectCartBean> call, Response<SelectCartBean> response) {
                SelectCartBean selectCartBean = response.body();
                selectCartModelCallBack.success(selectCartBean);
            }

            @Override
            public void onFailure(Call<SelectCartBean> call, Throwable t) {
                selectCartModelCallBack.failure();
            }
        });
    }
}
