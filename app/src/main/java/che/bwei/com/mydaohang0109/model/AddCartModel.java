package che.bwei.com.mydaohang0109.model;



import java.util.HashMap;
import java.util.Map;

import che.bwei.com.mydaohang0109.bean.AddCartBean;
import che.bwei.com.mydaohang0109.retrfit.IGetDataService;
import che.bwei.com.mydaohang0109.view.AddCartModelCallBack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Menglucywhh on 2017/12/7.
 */

public class AddCartModel {

  public void getData(String pid, final AddCartModelCallBack addCartModelCallBack) {
      // https://www.zhaoapi.cn/product/addCart?source=android&uid=1650&pid=57
      //"uid": 1650,
      // "token": "2FC3EF31EA25696D2715A971ADE38DE1",
      //"pid":57
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IGetDataService service = retrofit.create(IGetDataService.class);

        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid","1650");
        map.put("token","2FC3EF31EA25696D2715A971ADE38DE1");
        map.put("pid",pid);

      service.addCart(map).enqueue(new Callback<AddCartBean>() {
            @Override
            public void onResponse(Call<AddCartBean> call, Response<AddCartBean> response) {
                AddCartBean addCartBean = response.body();
                addCartModelCallBack.success(addCartBean);
            }

            @Override
            public void onFailure(Call<AddCartBean> call, Throwable t) {
                addCartModelCallBack.failure();
            }
        });
    }
}
