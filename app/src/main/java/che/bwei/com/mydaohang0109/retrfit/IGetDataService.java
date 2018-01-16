package che.bwei.com.mydaohang0109.retrfit;


import java.util.Map;

import che.bwei.com.mydaohang0109.bean.AddCartBean;
import che.bwei.com.mydaohang0109.bean.DeleteBean;
import che.bwei.com.mydaohang0109.bean.DengluBean;
import che.bwei.com.mydaohang0109.bean.FenLeiLeftBean;
import che.bwei.com.mydaohang0109.bean.FenLeiRightBean;
import che.bwei.com.mydaohang0109.bean.LoginBean;
import che.bwei.com.mydaohang0109.bean.RegistBean;
import che.bwei.com.mydaohang0109.bean.SearchBean;
import che.bwei.com.mydaohang0109.bean.SelectCartBean;
import che.bwei.com.mydaohang0109.bean.ZhuceBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2018/1/9 16:58
 */
public interface IGetDataService {


    //分类页面的左侧
    //https://www.zhaoapi.cn/product/getCatagory
    @GET("/product/getCatagory")
    Call<FenLeiLeftBean> getFenLei();

    /**
     * cid=1 休闲零食 京东生鲜 粮油调味 水饮茗茶 中外名酒
     * cid=2 数码家电 钟表 服饰奢品 美妆个护
     * cid=3 影音娱乐 手机通讯 手机配件 摄影摄像
     * cid=5 休闲裤 牛仔裤 男士内搭  男士外套
     * cid=6 裙装 上装  下装
     * */
    //分类页面的右侧
    //https://www.zhaoapi.cn/product/getProductCatagory?cid=1,2
    @FormUrlEncoded
    @POST("/product/getProductCatagory")
    Call<FenLeiRightBean> getFenLeiRight(@Field("cid") String cid);

    //注册的接口
    //https://www.zhaoapi.cn/user/reg?mobile=15810680959&password=123456
    @FormUrlEncoded
    @POST("/user/reg")
    Call<ZhuceBean> zhuce(@FieldMap Map<String,String> map);

    //http://120.27.23.105/user/login
    @GET("/user/login")
    Observable<LoginBean> getLogin(@Query("mobile") String mobile, @Query("password") String passwprd);
    @GET("/user/reg")
    Observable<RegistBean> getRegist(@Query("mobile") String mobile, @Query("password") String passwprd);


    //搜索 笔记本 手机的接口
    //https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1
    @GET("/product/searchProducts")
    Call<SearchBean> search(@QueryMap Map<String,String> map);

    // https://www.zhaoapi.cn/product/addCart?source=android&pid=57&uid=1650&token=2FC3EF31EA25696D2715A971ADE38DE1
    //"uid": 1650,
    // "token": "2FC3EF31EA25696D2715A971ADE38DE1",
    @GET("/product/addCart")
    Call<AddCartBean> addCart(@QueryMap Map<String,String> map);


    //https://www.zhaoapi.cn/product/getCarts?source=android&uid=1650&token=2FC3EF31EA25696D2715A971ADE38DE1
    //uid": 1650,
    // "token": "2FC3EF31EA25696D2715A971ADE38DE1",
    @GET("product/getCarts")
    Call<SelectCartBean> selectCart(@QueryMap Map<String,String> map);

    //删除
    //https://www.zhaoapi.cn/product/deleteCart?uid=1650&pid=58
    @GET("/product/deleteCart")
    Call<DeleteBean> deleteCart(@QueryMap Map<String,String> map);

}
