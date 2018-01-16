package che.bwei.com.mydaohang0109.presenter;


import che.bwei.com.mydaohang0109.bean.DeleteBean;
import che.bwei.com.mydaohang0109.model.DeleteCartModel;
import che.bwei.com.mydaohang0109.view.DeleteCartModelCallBack;
import che.bwei.com.mydaohang0109.view.DeleteCartViewCallBack;

public class DeleteCartPresenter {

    DeleteCartModel deleteCartModel = new DeleteCartModel();
    DeleteCartViewCallBack deleteCartViewCallBack;
    public DeleteCartPresenter(DeleteCartViewCallBack deleteCartViewCallBack) {
        this.deleteCartViewCallBack = deleteCartViewCallBack;
    }

    public void delete(String pid) {
        deleteCartModel.delete(pid, new DeleteCartModelCallBack() {
            @Override
            public void success(DeleteBean deleteBean) {
            deleteCartViewCallBack.success(deleteBean);
            }

            @Override
            public void failure() {
            deleteCartViewCallBack.failure();
            }
        });
    }
}
