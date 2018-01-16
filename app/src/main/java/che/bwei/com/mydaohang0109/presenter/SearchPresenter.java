package che.bwei.com.mydaohang0109.presenter;

import android.text.TextUtils;


import che.bwei.com.mydaohang0109.bean.SearchBean;
import che.bwei.com.mydaohang0109.model.SearchModel;
import che.bwei.com.mydaohang0109.view.SearchModelCallBack;
import che.bwei.com.mydaohang0109.view.SearchViewCallBack;

public class SearchPresenter {
    SearchModel searchModel = new SearchModel();
    SearchViewCallBack searchViewCallBack;
    public SearchPresenter(SearchViewCallBack searchViewCallBack) {
        this.searchViewCallBack = searchViewCallBack;
    }

    public void getData(String editSearch) {
        if(TextUtils.isEmpty(editSearch)||editSearch.length()==0){
            searchViewCallBack.empty();
            return;
        }
        if(!editSearch.equals("笔记本")&&!editSearch.equals("手机")){
            searchViewCallBack.falseEdit();
            return;
        }
        searchModel.getData(editSearch, new SearchModelCallBack() {
            @Override
            public void success(SearchBean searchBean) {
                searchViewCallBack.success(searchBean);
            }

            @Override
            public void failure() {
                searchViewCallBack.failure();
            }
        });

    }
}
