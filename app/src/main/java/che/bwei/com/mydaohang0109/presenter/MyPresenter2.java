package che.bwei.com.mydaohang0109.presenter;


import che.bwei.com.mydaohang0109.bean.ShouyeGridBean;
import che.bwei.com.mydaohang0109.model.ModelCallBack2;
import che.bwei.com.mydaohang0109.model.MyModel2;
import che.bwei.com.mydaohang0109.view.ViewCallBack2;

/**
 * Created by Menglucywhh on 2017/11/30.
 */
public class MyPresenter2 {

    MyModel2 myModel2 = new MyModel2();
    ViewCallBack2 viewCallBack2;

    public MyPresenter2(ViewCallBack2 viewCallBack2) {
        this.viewCallBack2 = viewCallBack2;
    }


    //请求轮播图数据
    public void getGrid(){
       //调用model层的方法 请求网络数据
        myModel2.getGrid(new ModelCallBack2() {

            @Override
            public void success(ShouyeGridBean shouyeGridBean) {
                viewCallBack2.success(shouyeGridBean);
            }

            @Override
            public void failure() {
                viewCallBack2.failure();
            }
        });
    }
}
