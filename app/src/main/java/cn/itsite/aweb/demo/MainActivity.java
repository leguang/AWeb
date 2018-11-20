package cn.itsite.aweb.demo;

import android.os.Bundle;
import android.widget.EditText;

import com.alibaba.android.arouter.launcher.ARouter;

import cn.itsite.abase.mvp.view.base.BaseActivity;
import cn.itsite.aweb.WebFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et = findViewById(R.id.et);

        findViewById(R.id.bt).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(WebFragment.KEY_LINK, et.getText().toString());
            ARouter.getInstance()
                    .build("/web/WebActivity")
                    .with(bundle)
                    .navigation();

//            Intent intent = new Intent(Intent.ACTION_VIEW);    //为Intent设置Action属性
//            intent.setData(Uri.parse("https://www.baidu.com")); //为Intent设置DATA属性
//            startActivity(intent);

        });
    }
}
