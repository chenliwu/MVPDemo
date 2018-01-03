package com.clw.mvpdemo1.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.clw.mvpdemo1.R;
import com.clw.mvpdemo1.base.BaseMvpActivity;
import com.clw.mvpdemo1.model.bean.User;
import com.clw.mvpdemo1.contract.LoginContract;
import com.clw.mvpdemo1.presenter.login.LoginPresenter;
import com.clw.mvpdemo1.ui.MainActivity;

/**
 * MVP中的View层：负责UI具体实现展现。
 * 实现View层，并且通过泛型说明View层接口和Presenter类
 * 另外，View层要实现对应的View接口
 */
public class LoginActivity extends BaseMvpActivity<LoginContract.ILoginView, LoginPresenter> implements LoginContract.ILoginView,View.OnClickListener {

    private EditText edt_username;
    private EditText edt_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initActivity();
        initView();
    }

    @Override
    protected void initActivity() {

    }

    @Override
    protected void initView() {
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_username.setOnClickListener(this);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_password.setOnClickListener(this);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (getUserName().length() > 0 && getPassword().length() > 0) {
                    mPresenter.login();
                } else {
                    showShortToast("用户名或密码输入不正确");
                }
                break;
        }
    }


    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    /////////////  View层接口 ///////////////

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public String getUserName() {
        return edt_username.getText().toString();
    }

    @Override
    public String getPassword() {
        return edt_password.getText().toString();
    }

    @Override
    public void clearUserName() {
        edt_username.setText("");
    }

    @Override
    public void clearPassword() {
        edt_password.setText("");
    }


    @Override
    public void toMainActivity(User user) {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        closeView();
    }

    @Override
    public void showMessage(String title, String content) {
        showShortToast(content);
    }

    @Override
    public void showErrorMessage(String title, String content) {
        showShortToast(content);
    }

    @Override
    public void showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("正在登录···");
        }
        mProgressDialog.show();
    }

    @Override
    public void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void closeView() {
        finish();
    }
}