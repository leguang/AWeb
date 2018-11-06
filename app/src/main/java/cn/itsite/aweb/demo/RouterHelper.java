package cn.itsite.aweb.demo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;

import cn.itsite.abase.mvp.view.base.BaseActivity;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.aweb.WebFragment;

/**
 * @author: leguang
 * @e-mail: langmanleguang@qq.com
 * @version: v0.0.0
 * @blog: https://github.com/leguang
 * @time: 2018/6/24 0024 11:05
 * @description:
 */
public class RouterHelper {
    public static final String MainActivity = "/home/MainActivity";
    public static final String LoginActivity = "/login/LoginActivity";
    public static final String ProfitFragment = "/profit/ProfitFragment";

    public static void go2Web(BaseFragment from, String url) {
        if (from == null || TextUtils.isEmpty(url)) {
            return;
        }
        BaseFragment to = (BaseFragment) ARouter.getInstance()
                .build("/web/WebFragment")
                .withString(WebFragment.KEY_LINK, url)
                .navigation();
        if (to != null) {
            from.start(to);
        }
    }

    public static void go2Web(BaseActivity activity, String url) {
        if (activity == null) {
            return;
        }
        BaseFragment to = (BaseFragment) ARouter.getInstance()
                .build("/web/WebFragment")
                .withString(WebFragment.KEY_LINK, url)
                .navigation();
        if (to != null) {
            activity.start(to);
        }
    }

    public static void go2Browser(BaseFragment from, String url) {
        if (from == null || TextUtils.isEmpty(url)) {
            return;
        }
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(from.getContext().getPackageManager()) != null) {
            from.startActivity(intent);
        }
    }

    public static void go2Browser(Context from, String url) {
        if (from == null || TextUtils.isEmpty(url)) {
            return;
        }
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(from.getPackageManager()) != null) {
            from.startActivity(intent);
        }
    }

    public static void go2(BaseFragment from, String url, Bundle bundle) {
        if (from == null || TextUtils.isEmpty(url)) {
            return;
        }
        BaseFragment fragment = (BaseFragment) ARouter.getInstance()
                .build(url)
                .with(bundle)
                .navigation();
        from.start(fragment);
    }

    public static void go2(BaseFragment from, String url) {
        go2(from, url, null);
    }

    public static void go2(BaseActivity activity, String url, Bundle bundle) {
        if (activity == null || TextUtils.isEmpty(url)) {
            return;
        }
        BaseFragment fragment = (BaseFragment) ARouter.getInstance()
                .build(url)
                .with(bundle)
                .navigation();
        activity.start(fragment);
    }

    public static void go2(BaseActivity activity, String url) {
        go2(activity, url, null);
    }

    public static void go2(String uri) {
        ARouter.getInstance()
                .build(uri)
                .navigation();
    }
}
