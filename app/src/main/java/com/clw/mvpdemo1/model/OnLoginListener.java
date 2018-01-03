package com.clw.mvpdemo1.model;

import com.clw.mvpdemo1.model.bean.User;

/**
 * 登录业务回调接口
 */
public interface OnLoginListener {
    /**
     * 登录成功回调方法
     * @param user
     */
    void loginSuccess(User user);

    /**
     * 登录失败回调方法
     */
    void loginFailed();
}
