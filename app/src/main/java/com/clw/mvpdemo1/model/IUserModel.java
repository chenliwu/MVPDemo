package com.clw.mvpdemo1.model;

/**
 * 与用户相关的业务逻辑接口
 */
public interface IUserModel {
    /**
     * 用户登录
     * @param username
     * @param password
     * @param loginListener
     */
    void login(String username, String password, OnLoginListener loginListener);
}
