package che.bwei.com.mydaohang0109.view;

import che.bwei.com.mydaohang0109.bean.SearchBean;

public interface SearchViewCallBack {
    public void success(SearchBean searchBean);
    public void empty();
    public void falseEdit();
    public void failure();
}
