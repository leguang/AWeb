package cn.itsite.aweb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.itsite.abase.mvp.view.base.BaseActivity;

/**
 * @author: leguang
 * @e-mail: langmanleguang@qq.com
 * @version: v0.0.0
 * @blog: https://github.com/leguang
 * @time: 2016/9/4 0004 11:50
 * @description: 负责项目中的web部分。
 */
@Route(path = "/web/WebActivity")
public class WebActivity extends BaseActivity {
    public static final String TAG = WebActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //目的是为了兼容两种启动方式
        //1.指定启动，这种的url是存Bundle里
        //2.隐式启动，与浏览器行为一致，url都是放date里。
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            Uri uri = intent.getData();
            bundle = new Bundle();
            if (uri != null) {
                bundle.putString(WebFragment.KEY_LINK, uri.toString());
            }
        }

        if (savedInstanceState == null) {
            loadRootFragment(android.R.id.content, WebFragment.getInstance(bundle));
        }
    }
}