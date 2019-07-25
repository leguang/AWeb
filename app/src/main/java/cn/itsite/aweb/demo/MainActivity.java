package cn.itsite.aweb.demo;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText et = findViewById(R.id.et);
        findViewById(R.id.bt).setOnClickListener(v -> {

//            Bundle bundle = new Bundle();
//            bundle.putString(WebFragment.KEY_LINK, TextUtils.isEmpty(et.getText().toString()) ? "https://www.baidu.com" : et.getText().toString());
//            ARouter.getInstance()
//                    .build("/web/WebActivity")
//                    .with(bundle)
//                    .navigation();

//            ARouter.openLog();     // 打印日志
//            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//            ARouter.init(getApplication());
//
//            ARouter.getInstance()
//                    .build("/web/WebActivity")
//                    .withString(WebFragment.KEY_LINK, TextUtils.isEmpty(et.getText().toString()) ? "https://www.baidu.com" : et.getText().toString())
//                    .navigation();

//            Intent intent = new Intent(Intent.ACTION_VIEW);    //为Intent设置Action属性
//            intent.setData(Uri.parse("https://www.baidu.com")); //为Intent设置DATA属性
//            startActivity(intent);

//            Intent intent = new Intent(this, WebActivity.class);
//            intent.setData(Uri.parse("https://www.baidu.com")); //为Intent设置DATA属性
//            startActivity(intent);

//            Intent intent = new Intent(this, WebActivity.class);
//            intent.putExtra(WebFragment.KEY_LINK, TextUtils.isEmpty(et.getText().toString()) ? "https://www.baidu.com" : et.getText().toString());
//            startActivity(intent);

        });

//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fl, new MainFragment())
//                .commit();

    }
}
