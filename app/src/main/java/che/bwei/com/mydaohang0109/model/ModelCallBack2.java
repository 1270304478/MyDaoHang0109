package che.bwei.com.mydaohang0109.model;


import che.bwei.com.mydaohang0109.bean.ShouyeGridBean;

/**
 * Created by Menglucywhh on 2017/11/30.
 */

public interface ModelCallBack2 {
    //九宫格的接口
    public void success(ShouyeGridBean shouyeGridBean);
    public void failure();
}
