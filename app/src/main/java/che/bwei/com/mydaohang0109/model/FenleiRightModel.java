package che.bwei.com.mydaohang0109.model;



import che.bwei.com.mydaohang0109.bean.FenLeiRightBean;
import che.bwei.com.mydaohang0109.retrfit.IGetDataService;
import che.bwei.com.mydaohang0109.view.FenleiRightModelCallBack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Menglucywhh on 2017/12/6.
 */

public class FenleiRightModel {
    //右边分类网址
    //https://www.zhaoapi.cn/product/getProductCatagory?cid=1,2,3,5,6
    public void getData(final FenleiRightModelCallBack callBack, String cid){
        //
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IGetDataService service = retrofit.create(IGetDataService.class);

        if(!cid.equals("1")&&!cid.equals("2")&&!cid.equals("3")&&!cid.equals("5")&&!cid.equals("6")){
            cid="3";
        }
        service.getFenLeiRight(cid).enqueue(new Callback<FenLeiRightBean>() {

            @Override
            public void onResponse(Call<FenLeiRightBean> call, Response<FenLeiRightBean> response) {
                FenLeiRightBean rightBean = response.body();
                callBack.success(rightBean);
            }

            @Override
            public void onFailure(Call<FenLeiRightBean> call, Throwable t) {
                 callBack.failure();
            }
        });
    }

    }

