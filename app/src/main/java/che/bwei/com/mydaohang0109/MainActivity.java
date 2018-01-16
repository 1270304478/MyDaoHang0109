package che.bwei.com.mydaohang0109;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import che.bwei.com.mydaohang0109.activity.HomeActivty;
import che.bwei.com.mydaohang0109.activity.ThreeActivity;
public class MainActivity extends Activity {
    @BindView(R.id.mytext)
    TextView mytext;
    @BindView(R.id.tiaozhuan)
    TextView tiaozhuan;
    private int time =5;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:

                  /*  time--;
                    mytext.setText("倒计时:" + time + "秒");
                    Intent intent = new Intent(MainActivity.this, ThreeActivity.class);
                    startActivity(intent);
                    finish();*/

                    if(time > 0){
                        time--;
                        mytext.setText("倒计时:"+time+"秒");
                        Message message = handler.obtainMessage(0);
                        handler.sendMessageDelayed(message, 1000);      // send message
                    }else{
                        startActivity(new Intent(MainActivity.this,ThreeActivity.class));
                        finish();
                    }
            break;
            case 1:
            Intent intent1 = new Intent(MainActivity.this, HomeActivty.class);
            startActivity(intent1);
            finish();
            break;
        }
            super.handleMessage(msg);
    }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SharedPreferences preferences = getSharedPreferences("config", 0);
        boolean flag = preferences.getBoolean("flag", false);

        if (flag) {
            //第二次进入页面,不延迟直接跳转
            handler.sendEmptyMessage(1);
        } else {
            //第一次进入页面,,存值为true,,,延迟跳转
            preferences.edit().putBoolean("flag", true).commit();
            Message message = handler.obtainMessage(0);     // Message
            //  handler.sendEmptyMessageDelayed(0, 5000);
            handler.sendMessageDelayed(message, 1000);      // send message
        }
        tiaozhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ThreeActivity.class));

            }
        });
    }
}
