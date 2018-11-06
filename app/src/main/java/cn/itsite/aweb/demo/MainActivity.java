package cn.itsite.aweb.demo;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

import cn.itsite.abase.mvp.view.base.BaseActivity;
import cn.itsite.aweb.WebFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(WebFragment.KEY_LINK, "https://www.baidu.com/");
//                RouterHelper.go2(MainActivity.this, "/web/WebFragment", bundle);

                ARouter.getInstance()
                        .build("/web/WebActivity")
                        .with(bundle)
                        .navigation();
            }
        });
    }
}
