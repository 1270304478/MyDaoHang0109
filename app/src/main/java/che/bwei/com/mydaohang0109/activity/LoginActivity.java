package che.bwei.com.mydaohang0109.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import che.bwei.com.mydaohang0109.MainActivity;
import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.bean.LoginBean;
import che.bwei.com.mydaohang0109.utils.MyAppliction;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
public class LoginActivity extends Activity {

    @BindView(R.id.denglu_word)
    EditText dengluWord;
    @BindView(R.id.denglu_pwd)
    EditText dengluPwd;


    private static final String TAG = "MainActivity";
    private static final String APP_ID = "13681462126";
    @BindView(R.id.denglu_cha)
    TextView dengluCha;
    @BindView(R.id.denglu_btn)
    Button dengluBtn;
    @BindView(R.id.zhuce)
    TextView zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }
    @OnClick({R.id.denglu_cha, R.id.denglu_btn, R.id.zhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.denglu_cha:
                Intent intent = new Intent(LoginActivity.this, HomeActivty.class);
                startActivity(intent);
                finish();
                break;
            case R.id.denglu_btn:
                String p = dengluWord.getText().toString().trim();
                String pa = dengluPwd.getText().toString().trim();
                  encode(pa);
                MyAppliction.api.getLogin(p, pa)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<LoginBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }
                            @Override
                            public void onNext(LoginBean loginBean) {
                                String code = loginBean.getCode();
                                if (code.equals("0")) {
                                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    int uid = loginBean.getData().getUid();
                                    String mobile = loginBean.getData().getMobile();
                                    SharedPreferences sp = getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor edit = sp.edit();
                                    edit.putString("name", mobile);
                                    edit.putInt("uid", uid);
                                    edit.commit();
                                    System.out.println("传值1" + edit.putString("name", mobile));
                                    System.out.println("传值2" + edit.putString("uid", uid + ""));
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
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
            case R.id.zhuce:
                Intent intenta = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intenta);
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private static void encode(String psd) {
        try {
            //指定加密算法类型
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //将字符串转换程byte[]类型的数组，然后进行随机哈希过程打乱
            byte[] bs = digest.digest(psd.getBytes());
            //3,循环遍历bs,然后让其生成32位字符串,固定写法
            //4,拼接字符串过程
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bs) {
                int i = b & 0xff;
                //int类型的i需要转换成16机制字符
                String hexString = Integer.toHexString(i);
                if(hexString.length()<2){
                    hexString = "0"+hexString;
                }
                stringBuffer.append(hexString);

            }
            //5,打印测试
            System.out.println("MD5加密值"+stringBuffer.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
