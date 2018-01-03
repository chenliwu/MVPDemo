package com.clw.mvpdemo1.model;


import com.clw.mvpdemo1.model.bean.User;

/**
 * MVP中的Model层：数据层，在这个类当中实现具体的业务逻辑操作，网络操作、访问服务器获取数据等。<p></p>
 * 用户业务逻辑的实现类<p>
 * 在这个类当中实现具体的业务逻辑操作，网络操作、访问服务器获取数据等，再通过相关的Listener调用相应的回调方法。
 */
public class UserModelImpl implements IUserModel {
    @Override
    public void login(final String username,final String password,final OnLoginListener loginListener) {
        //模拟子线程耗时操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("123".equals(username) && "123".equals(password)) {
                    //模拟登录成功
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    loginListener.loginSuccess(user);
                } else {
                    //模拟登录失败
                    loginListener.loginFailed();
                }
            }
        }.start();
    }
}
