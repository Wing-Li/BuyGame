package com.lyl.buygame.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import com.lyl.buygame.view.TransitionHelper;

/**
 * Author: lyl
 * Date Created : 2017/11/8.
 */
public abstract class BaseFragment extends Fragment {

    protected View rootView;
    protected ActionBar mActionBar;

    private BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResource(), container, false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }

    protected abstract int getLayoutResource();


    public BaseActivity getmActivity() {
        if (mActivity == null) {
            throw new IllegalArgumentException("the acticity must be extends BaseActivity");
        }
        return mActivity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected void showToast(String str) {
        Toast.makeText(getmActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int res) {
        Toast.makeText(getmActivity().getApplicationContext(), res, Toast.LENGTH_SHORT).show();
    }


    /**
     * 跳转页面
     *
     * @param intent
     * @param includeStatusBar 如果是false，状态栏将不会被添加为过渡参与者
     */
    public void skipActivity(Intent intent, boolean includeStatusBar) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(getmActivity(), false);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getmActivity(), pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

}
