package che.bwei.com.mydaohang0109.model;


import che.bwei.com.mydaohang0109.bean.ShouyeGridBean;
import che.bwei.com.mydaohang0109.okhttp.AbstractUiCallBack;
import che.bwei.com.mydaohang0109.okhttp.OkhttpUtils;

/**
 * Created by Menglucywhh on 2017/11/30.
 */

public class MyModel2 {
    //model层里面调用okhttp封装类 请求数据
    public void getGrid(final ModelCallBack2 modelCallBack2) {
        String path = "http://120.27.23.105/product/getCatagory";
        OkhttpUtils.getInstance().asy(null, path, new AbstractUiCallBack<ShouyeGridBean>() {

            @Override
            public void success(ShouyeGridBean shouyeGridBean) {
                  modelCallBack2.success(shouyeGridBean);
            }

            @Override
            public void failure(Exception e) {
                modelCallBack2.failure();
            }
        });
    }
}
