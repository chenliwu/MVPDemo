package com.clw.mvpdemo1.ui;

import android.os.Bundle;
import com.clw.mvpdemo1.R;
import com.clw.mvpdemo1.base.BaseAppComatActivity;

public class MainActivity extends BaseAppComatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
        initView();
    }

    @Override
    protected void initActivity() {
        setCouldDoubleBackExit(true);
    }

    @Override
    protected void initView() {

    }
}
