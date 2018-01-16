package che.bwei.com.mydaohang0109.presenter;


import che.bwei.com.mydaohang0109.bean.FenLeiRightBean;
import che.bwei.com.mydaohang0109.model.FenleiRightModel;
import che.bwei.com.mydaohang0109.view.FenleiRightModelCallBack;
import che.bwei.com.mydaohang0109.view.FenleiRightViewCallBack;

public class FenleiRightPresenter {
    private FenleiRightModel fenleiRightModel;
    FenleiRightViewCallBack fenleiRightViewCallBack;
    public FenleiRightPresenter(FenleiRightViewCallBack fenleiRightViewCallBack){
        this.fenleiRightViewCallBack =fenleiRightViewCallBack;
        fenleiRightModel = new FenleiRightModel();
    }

    public void getData(String cid){
        fenleiRightModel.getData(new FenleiRightModelCallBack() {
            @Override
            public void success(FenLeiRightBean fenLeiRightBean) {
                fenleiRightViewCallBack.success(fenLeiRightBean);
            }

            @Override
            public void failure() {
                fenleiRightViewCallBack.failure();
            }
        },cid);
    }
}
