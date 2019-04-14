package com.lyl.buygame.ui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import com.lyl.buygame.view.TransitionHelper;

/**
 * Author: lyl
 * Date Created : 2017/11/8.
 */
public class BaseActivity extends AppCompatActivity {

    protected Activity mActivity;
    protected Context mContext;
    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected void showToast(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int res) {
        Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
    }

    /**
     * 跳转页面
     *
     * @param intent
     * @param includeStatusBar 如果是false，状态栏将不会被添加为过渡参与者
     */
    public void skipActivity(Intent intent, boolean includeStatusBar) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, false);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    /**
     * 透明状态栏 和 底部按键透明
     */
    protected void translucentStatusAndNavigation() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    /**
     * 显示加载圈
     */
    protected void showLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("Loading...");
        }
        mProgressDialog.show();
    }

    /**
     * 隐藏加载圈
     */
    protected void hideLoadingDialog(){
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    /**
     * 底部按键透明
     */
    protected void translucentNavigation() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    public void t(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 防止 String 为 null
     *
     * @param s
     * @return
     */
    public String FS(String s) {
        if (TextUtils.isEmpty(s)) {
            return "";
        } else {
            return s;
        }
    }
}
