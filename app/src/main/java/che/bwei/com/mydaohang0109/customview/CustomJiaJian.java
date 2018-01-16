package che.bwei.com.mydaohang0109.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;



import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import che.bwei.com.mydaohang0109.R;

/**
 * Created by Menglucywhh on 2017/12/9.
 */

public class CustomJiaJian extends LinearLayout {

    @BindView(R.id.reverse)
    Button reverse;
    @BindView(R.id.count)
    EditText countEdit;
    @BindView(R.id.add)
    Button add;
    private int mCount =1;
    Context context;
    public CustomJiaJian(Context context) {
        super(context);
    }

    public CustomJiaJian(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = View.inflate(context, R.layout.custom_jiajian, this);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.reverse, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reverse://减号
                String content = countEdit.getText().toString().trim();
                int count = Integer.valueOf(content);

                if (count > 1) {
                    mCount = count - 1;
                    countEdit.setText(mCount + "");
                    //回调给adapter里面
                    if (customListener != null) {
                        customListener.jiajian(mCount);
                    }
                 }else{
                    Toast.makeText(context, "最小数量为1", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.add://加号
                String content1 = countEdit.getText().toString().trim();
                int count1 = Integer.valueOf(content1)+1;
                mCount = count1;

                countEdit.setText(mCount+"");

                //接口回调给adapter
                if(customListener!=null){
                    customListener.jiajian(mCount);
                }
                break;
        }
    }

    public CustomJiaJian(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    CustomListener customListener;
    public void setCustomListener(CustomListener customListener){
        this.customListener = customListener;
    }

    //加减的接口
    public interface CustomListener{
        public void jiajian(int count);
    }

    //这个方法是供recyadapter设置 数量时候调用的
    public void setEditText(int num) {
        if(countEdit !=null) {
            countEdit.setText(num + "");
        }
    }
}
