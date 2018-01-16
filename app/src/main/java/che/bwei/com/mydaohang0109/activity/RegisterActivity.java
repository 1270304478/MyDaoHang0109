package che.bwei.com.mydaohang0109.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.bean.LoginBean;
import che.bwei.com.mydaohang0109.bean.RegistBean;
import che.bwei.com.mydaohang0109.utils.MyAppliction;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class RegisterActivity extends Activity {

    @BindView(R.id.zhuce_cha)
    TextView zhuceCha;
    @BindView(R.id.zhuce_word)
    EditText zhuceWord;
    @BindView(R.id.zhuce_pwd)
    EditText zhucePwd;
    @BindView(R.id.zhuce_btn)
    Button zhuceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.zhuce_cha, R.id.zhuce_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuce_cha:
                finish();
                break;
            case R.id.zhuce_btn:
                String p = zhuceWord.getText().toString().trim();
                String pa = zhucePwd.getText().toString().trim();

              MyAppliction.api.getRegist(p, pa)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RegistBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RegistBean registBean) {
                                String code = registBean.getCode();
                                if (code.equals("0")) {
                                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                break;
        }
    }
}
