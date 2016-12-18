package com.example.sah.lesson_7;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends MainMenu {

    Button btn;
    Button btn_reset;
    TextView textView;
    TextView tv_progress;
    private Handler handler;
    int i = 0;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tv_progress = (TextView) findViewById(R.id.tv_progress);

        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {

                if (msg.what < 5){
                    progressBar.setProgress(msg.what);
                    tv_progress.setText(R.string.operation_start);
                    textView.setText("" + msg.what);
                }
                else if (msg.what == 5){
                    tv_progress.setText(R.string.operation_finish);
                    progressBar.setProgress(msg.what);
                    textView.setText("" + msg.what);
                    btn.setEnabled(true);
                }
            }
        };


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setEnabled(false);
                Thread thread = new Thread() {
                    public void run() {
                        for (int i = 1; i <= 5; i++) {
                            some_method();
                            handler.sendEmptyMessage(i);
                        }
                    }
                };
                thread.start();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv_progress.setText(" ");
                textView.setText("0");
                progressBar.setProgress(0);
                i = 0;
            }
        });

    }



    public void some_method() {
        //Иммитация работы
        try {
           TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
