package com.example.sah.lesson_7;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class SecondActivity extends MainMenu {

    Button btn;
    TextView tv_progress;
    ProgressBar progressBar;
    MyTask myTask;
    Button btn_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        btn = (Button) findViewById(R.id.btn_b);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_b);
        tv_progress = (TextView) findViewById(R.id.tv_progress_b);
        btn_result = (Button) findViewById(R.id.buttonResult);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTask = new MyTask();
                myTask.execute();
            }
        });


        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myTask == null) return;
                try {
                    String result = myTask.get();
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public class MyTask extends AsyncTask<String, Integer, String> {

        static final int PROGRESS_MAX = 5;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setProgress(0);
            tv_progress.setText(R.string.operation_start);
            btn.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... params) {
            int i;
            for (i = 1; i <= 5; i++) {
                some_method();
                publishProgress(i);
            }

            return ("Операция выполнена");
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            tv_progress.setText(R.string.operation_finish);
            progressBar.setProgress(PROGRESS_MAX);
            btn.setEnabled(true);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }


        private void some_method() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}
