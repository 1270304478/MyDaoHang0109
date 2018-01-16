package che.bwei.com.mydaohang0109.presenter;


import che.bwei.com.mydaohang0109.bean.SelectCartBean;
import che.bwei.com.mydaohang0109.model.SelectCartModel;
import che.bwei.com.mydaohang0109.view.SelectCartModelCallBack;
import che.bwei.com.mydaohang0109.view.SelectCartViewCallBack;


public class SelectCartPresenter {
    SelectCartModel selectCartModel = new SelectCartModel();

    SelectCartViewCallBack selectCartViewCallBack;
    public SelectCartPresenter(SelectCartViewCallBack selectCartViewCallBack) {
        this.selectCartViewCallBack = selectCartViewCallBack;
    }
    //调用model 层的请求数据
    public void getData(){
        selectCartModel.getData(new SelectCartModelCallBack() {
            @Override
            public void success(SelectCartBean selectCartBean) {
                if(selectCartViewCallBack!=null) {
                    selectCartViewCallBack.success(selectCartBean);
                }
            }

            @Override
            public void failure() {
                if(selectCartViewCallBack!=null) {
                    selectCartViewCallBack.failure();
                }
            }
        });
    }

    /**
     * 防止内存泄露
     * */
    public void detach(){
        selectCartViewCallBack=null;
    }
}
