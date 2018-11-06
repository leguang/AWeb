package cn.itsite.aweb;

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
        Bundle extras = getIntent().getExtras();
        if (savedInstanceState == null) {
            loadRootFragment(android.R.id.content, WebFragment.getInstance(extras));
        }
    }
}
