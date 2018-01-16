package che.bwei.com.mydaohang0109.presenter;

import che.bwei.com.mydaohang0109.bean.ShouYeLunBoBean;
import che.bwei.com.mydaohang0109.model.ModelCallBack1;
import che.bwei.com.mydaohang0109.model.MyModel1;
import che.bwei.com.mydaohang0109.view.ViewCallBack1;

/**
 * 此类的作用：
 * 轮播逻辑代码
 *
 * @author: forever
 * @date: 2018/1/9 16:52
 */
public class MyPresenter1 {
    MyModel1 myModel1 = new MyModel1();
    ViewCallBack1 viewCallBack1;

    public MyPresenter1(ViewCallBack1 viewCallBack1) {
        this.viewCallBack1 = viewCallBack1;
    }

    //请求数据
    public void getLunBo() {
        myModel1.getLunBo(new ModelCallBack1() {
            @Override
            public void success(ShouYeLunBoBean shouYeLunBoBean) {
                viewCallBack1.success(shouYeLunBoBean);
            }

            @Override
            public void failure() {
                viewCallBack1.failure();
            }
        });
    }
}
