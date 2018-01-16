package che.bwei.com.mydaohang0109.utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import che.bwei.com.mydaohang0109.retrfit.IGetDataService;
import retrofit2.Retrofit;


import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2018/1/9 17:04
 */
public class MyAppliction extends Application {
    public static IGetDataService api;
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(configuration);
        Fresco.initialize(this);
        ImageLoderUtil.initConfig(this);
        //二维码初始化
        ZXingLibrary.initDisplayOpinion(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.23.105")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(IGetDataService.class);

    }
}
