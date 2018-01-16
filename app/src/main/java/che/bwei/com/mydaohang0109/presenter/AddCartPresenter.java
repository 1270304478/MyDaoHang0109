package che.bwei.com.mydaohang0109.presenter;


import che.bwei.com.mydaohang0109.bean.AddCartBean;
import che.bwei.com.mydaohang0109.model.AddCartModel;
import che.bwei.com.mydaohang0109.view.AddCartModelCallBack;
import che.bwei.com.mydaohang0109.view.AddCartViewCallBack;

/**
 * Created by Menglucywhh on 2017/12/7.
 */

public class AddCartPresenter {
    AddCartModel addCartModel = new AddCartModel();

    AddCartViewCallBack addCartViewCallBack;
    public AddCartPresenter(AddCartViewCallBack addCartViewCallBack) {
        this.addCartViewCallBack = addCartViewCallBack;
    }

    public void getData(String pid) {

        addCartModel.getData(pid, new AddCartModelCallBack() {
            @Override
            public void success(AddCartBean addCartBean) {
                addCartViewCallBack.success(addCartBean);
            }

            @Override
            public void failure() {
                addCartViewCallBack.failure();
            }
        });

    }
}
