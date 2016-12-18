package com.example.sah.lesson_7;


import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class ThirdActivity extends MainMenu implements LoaderManager.LoaderCallbacks<String> {



    static final int LOADER_ID = 1;
    TextView textView;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        textView = (TextView) findViewById(R.id.tv_loader);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_c);
        progressBar.setVisibility(View.INVISIBLE);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Loader<String> mLoader = null;
        if (id == LOADER_ID) {
            mLoader = new MyLoader(this);
        }
        textView.setText(R.string.operation_start);
        progressBar.setVisibility(View.VISIBLE);
        return mLoader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        progressBar.setVisibility(View.GONE);
        textView.setText(data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

}