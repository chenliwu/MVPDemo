package com.clw.mvpdemo1.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.clw.mvpdemo1.ui.MainActivity;

/**
 * 抽象BaseAppComatActivity类
 */
public abstract class BaseAppComatActivity extends AppCompatActivity {

    private Toast mToast;

    /**
     * 双击退出
     */
    private boolean couldDoubleBackExit;
    private boolean doubleBackExitPressedOnce;

    protected Context mContext;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果是退出应用flag,则直接关闭当前页面,不加载UI
        boolean exit = getIntent().getBooleanExtra("exit", false);
        if (exit) {
            finish();
            return;
        }
        mContext=this;
    }

    /**
     * 初始化Activity
     */
    protected abstract void initActivity();

    /**
     * 初始化控件
     */
    protected abstract void initView();




    /**
     * 显示短时间Toast
     * @param content 显示文本内容
     */
    protected void showShortToast(String content){
        if(content!=null && !content.isEmpty()){
            showToast(content, Toast.LENGTH_LONG);
        }
    }

    /**
     * 显示长时间Toast
     * @param content 显示文本内容
     */
    protected void showLongToast(String content){
        if(content!=null && !content.isEmpty()){
            showToast(content, Toast.LENGTH_SHORT);
        }
    }

    /**
     * 非阻塞试显示Toast,防止出现连续点击Toast时的显示问题
     * @param text 提示内容
     * @param duration 显示时长
     */
    private void showToast(CharSequence text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    /**
     * 设置是否可以双击返回退出，需要有该功能的页面set true即可
     * @param couldDoubleBackExit true-开启双击退出
     */
    public void setCouldDoubleBackExit(boolean couldDoubleBackExit) {
        this.couldDoubleBackExit = couldDoubleBackExit;
    }

    @Override
    public void onBackPressed() {
        if (!couldDoubleBackExit) {
            // 非双击退出状态，使用原back逻辑
            super.onBackPressed();
            return;
        }
        // 双击返回键关闭程序
        // 如果两秒重置时间内再次点击返回,则退出程序
        if (doubleBackExitPressedOnce) {
            exit();
            return;
        }
        doubleBackExitPressedOnce = true;
        showShortToast("再按一次返回键关闭程序");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 延迟两秒后重置标志位为false
                doubleBackExitPressedOnce = false;
            }
        },2000);
    }

    /**
     * 退出程序
     */
    protected void exit() {
        // 退出程序方法有多种
        // 这里使用clear + new task的方式清空整个任务栈,只保留新打开的Main页面
        // 然后Main页面接收到退出的标志位exit=true,finish自己,这样就关闭了全部页面
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("exit", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
