package com.clw.mvpdemo1.presenter.login;

import android.util.Log;
import com.clw.mvpdemo1.base.BasePresenter;
import com.clw.mvpdemo1.model.bean.User;
import com.clw.mvpdemo1.contract.LoginContract;
import com.clw.mvpdemo1.model.IUserModel;
import com.clw.mvpdemo1.model.OnLoginListener;
import com.clw.mvpdemo1.model.UserModelImpl;

/**
 * MVP中的Presenter层：负责处理业务逻辑代码，处理Model数据，然后分发给View层的抽象接口。<p></p>
 * Presenter是用作Model和View之间交互的桥梁。
 * 应用程序主要的程序逻辑在Presenter内实现，Presenter将Model和View完全分离、所有的交互都发生在Presenter内部
 * 其实也是主要看该功能有什么操作，比如本例，两个操作:login和clear。<p></p>
 *
 * 在Presenter实现类中，要通过泛型说明视图的类型。
 */
public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements LoginContract.ILoginPresenter{

    private final static String TAG="LoginPresenter";

    private IUserModel mUserModel;

    public LoginPresenter(){
        Log.i(TAG,"LoginPresenter()");
        mUserModel=new UserModelImpl();
    }

    @Override
    public void login() {
        mView.showProgress();
        mUserModel.login(mView.getUserName(), mView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(!mView.isActive()){
                            //当视图被销毁后，停止UI操作
                            return ;
                        }
                        mView.dismissProgress();
                        mView.showMessage("登录成功","登录成功");
                        mView.toMainActivity(null);
                    }
                });
            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(!mView.isActive()){
                            //当视图被销毁后，停止UI操作
                            return ;
                        }
                        mView.dismissProgress();
                        mView.showMessage("登录失败","登录失败");
                    }
                });
            }
        });
    }
}
