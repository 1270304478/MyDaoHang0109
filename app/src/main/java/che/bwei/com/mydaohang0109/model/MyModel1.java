package che.bwei.com.mydaohang0109.model;

import che.bwei.com.mydaohang0109.bean.ShouYeLunBoBean;
import che.bwei.com.mydaohang0109.okhttp.AbstractUiCallBack;
import che.bwei.com.mydaohang0109.okhttp.OkhttpUtils;
/**
 * 此类的作用：
 * 轮播图数据
 * @author: forever
 * @date: 2018/1/9 16:33
 */
public class MyModel1 {
    //okhttp封装 请求数据
    public void getLunBo(final ModelCallBack1 modelCallBack1){
        String path="http://120.27.23.105/ad/getAd";

        OkhttpUtils.getInstance().asy(null,path, new AbstractUiCallBack<ShouYeLunBoBean>() {
            @Override
            public void success(final ShouYeLunBoBean shouYeLunBoBean) {
                modelCallBack1.success(shouYeLunBoBean);
        }
            @Override
            public void failure(Exception e) {
             modelCallBack1.failure();
            }
        });
    }
}

