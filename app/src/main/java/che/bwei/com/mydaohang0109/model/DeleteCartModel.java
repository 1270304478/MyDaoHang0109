package che.bwei.com.mydaohang0109.model;



import java.util.HashMap;
import java.util.Map;

import che.bwei.com.mydaohang0109.bean.DeleteBean;
import che.bwei.com.mydaohang0109.retrfit.IGetDataService;
import che.bwei.com.mydaohang0109.view.DeleteCartModelCallBack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Menglucywhh on 2017/12/11.
 */

public class DeleteCartModel {

    public void delete(String pid, final DeleteCartModelCallBack deleteCartModelCallBack) {
        //https://www.zhaoapi.cn/product/deleteCart?uid=1650&pid=58
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IGetDataService service = retrofit.create(IGetDataService.class);

        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid","1650");
        map.put("pid",pid);

        service.deleteCart(map).enqueue(new Callback<DeleteBean>() {
            @Override
            public void onResponse(Call<DeleteBean> call, Response<DeleteBean> response) {
                DeleteBean deleteBean = response.body();
                deleteCartModelCallBack.success(deleteBean);
            }

            @Override
            public void onFailure(Call<DeleteBean> call, Throwable t) {
                deleteCartModelCallBack.failure();
            }
        });
    }

}
