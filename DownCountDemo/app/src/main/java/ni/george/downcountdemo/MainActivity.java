package ni.george.downcountdemo;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    private Handler mHandler;
    private EditText tvMinute;
    private EditText tvSecond;
    private Button btStart;
    private int minute=0;
    private int second=0;
    private long allTime=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mHandler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                System.out.println(""+allTime);
                switch (msg.what){
                    case 1:
                        if(allTime>0){
                            btStart.setBackgroundColor(getResources().getColor(R.color.light_grey));
                            btStart.setClickable(false);
                            if(minute>0){
                                if(second!=0){
                                    second-=1;
                                }else {
                                    second=59;
                                    minute-=1;
                                }
                            }else if(minute==0){
                                if(second!=0){
                                    second-=1;
                                }else {
                                    second=0;
                                }
                            }
                            allTime-=1000;
                            tvMinute.setText("" + minute);
                            tvSecond.setText(""+second);
                            mHandler.sendEmptyMessageDelayed(1, 1000);
                        }else {
                            btStart.setBackgroundColor(getResources().getColor(R.color.button_green));
                            btStart.setClickable(true);
                            tvMinute.setText("10");
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void initViews() {
        tvMinute= (EditText) findViewById(R.id.tv_minute);
        tvSecond= (EditText) findViewById(R.id.tv_second);
        btStart= (Button) findViewById(R.id.start);

    }


    public void startTime(View view){
        String strMinute=String.valueOf(tvMinute.getText());
        String strSecond=String.valueOf(tvSecond.getText());
        if(TextUtils.isEmpty(strMinute)){
            strMinute="0";
        }
        if(TextUtils.isEmpty(strSecond)){
            strSecond="0";
        }
        minute=Integer.parseInt(strMinute);
        second=Integer.parseInt(strSecond);
        System.out.println("minute= "+minute);
        System.out.println("second= "+second);
        allTime=(minute*60+second)*1000;
        mHandler.sendEmptyMessageDelayed(1,0);
    }

    public void resetTime(View view){
        allTime=0;
        tvMinute.setText("10");
        tvSecond.setText("00");
    }
}
