package com.example.sah.lesson_7;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import java.util.concurrent.TimeUnit;

public class MyLoader extends AsyncTaskLoader<String> {


    public MyLoader(Context context) {
        super(context);
    }


    @Override
    public String loadInBackground() {

        //Иммитация работы
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String str = "Операция выполнена";
        return str;
    }

    @Override
    public void forceLoad() {
        super.forceLoad();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }

    @Override
    public void deliverResult(String data) {
        super.deliverResult(data);
    }


}
