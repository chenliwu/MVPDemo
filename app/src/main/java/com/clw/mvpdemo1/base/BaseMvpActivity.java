package com.clw.mvpdemo1.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * MVP框架模式的View层基类，用于给Activity继承
 * presenter绑定到activity和View的绑定和解绑操作是每个Activity都会去做的，用父类通过泛型统一完成这个工作。
 * @param <V> View层接口
 * @param <P> Presenter层类
 */
public abstract class BaseMvpActivity<V,P extends BasePresenter<V>> extends BaseAppComatActivity  {

    final static String TAG="BaseMvpActivity";

    protected ProgressDialog mProgressDialog;

    /**
     * Presenter对象
     */
    protected P mPresenter;

    /**
     * 判断视图是否还在活动
     */
    protected boolean isActive;

    /**
     * 实例化Presenter，注意是实现泛型指定的Presenter
     * @return
     */
    protected abstract P initPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume：绑定视图");
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        isActive = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isActive = false;
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"onDestroy：解绑视图");
        mPresenter.detachView();
        super.onDestroy();
    }

}
