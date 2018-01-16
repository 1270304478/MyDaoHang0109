package che.bwei.com.mydaohang0109.presenter;


import che.bwei.com.mydaohang0109.bean.FenLeiLeftBean;
import che.bwei.com.mydaohang0109.model.MyFenleiListViewModel;
import che.bwei.com.mydaohang0109.view.FenleiListModelCallBack;
import che.bwei.com.mydaohang0109.view.FenleiListViewCallBack;

public class MyFenleiListViewPresenter {

    MyFenleiListViewModel myFenleiListViewModel = new MyFenleiListViewModel();
    FenleiListViewCallBack fenleiListViewCallBack;
    public MyFenleiListViewPresenter(FenleiListViewCallBack fenleiListViewCallBack) {
        this.fenleiListViewCallBack = fenleiListViewCallBack;
    }

    public void getData(){
        myFenleiListViewModel.getData(new FenleiListModelCallBack() {
            @Override
            public void success(FenLeiLeftBean fenLeiLeftBean) {
                fenleiListViewCallBack.success(fenLeiLeftBean);
            }

            @Override
            public void failure() {
                fenleiListViewCallBack.failure();
            }
        });
    }
}
