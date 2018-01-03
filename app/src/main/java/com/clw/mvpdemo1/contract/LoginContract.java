package com.clw.mvpdemo1.contract;


import com.clw.mvpdemo1.base.IBaseView;
import com.clw.mvpdemo1.model.bean.User;

/**
 * 登录业务契约接口
 * <p>Contract其实就是一个包涵了Presenter和View的接口，Presenter实现的逻辑层方法，
 * View实现的UI层的方法都能在Contract接口中一目了然的看明白，
 * 具体的Presenter和View的实现类都是通过实现Contract接口来完成。这种方式既方便了管理和维护，也给开发点了一个导航灯。</p>
 */
public interface LoginContract {

    /**
     * 登录视图层接口
     */
    interface ILoginView extends IBaseView {
        /**
         * 获取用户名
         * @return
         */
        String getUserName();

        /**
         * 获取密码
         * @return
         */
        String getPassword();

        /**
         * 清除用户名
         */
        void clearUserName();

        /**
         * 清除密码
         */
        void clearPassword();

        /**
         * 登录成功，进入主界面
         * @param user
         */
        void toMainActivity(User user);

    }

    /**
     * 登录Presenter层接口
     */
    interface ILoginPresenter {
        /**
         * 用户登录
         */
        void login();
    }
}
