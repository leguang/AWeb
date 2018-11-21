package cn.itsite.aweb.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * @author: leguang
 * @e-mail: langmanleguang@qq.com
 * @version: v0.0.0
 * @blog: https://github.com/leguang
 * @time: 2018/11/21 0021 20:00
 * @description:
 */
public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText et = view.findViewById(R.id.et);

        view.findViewById(R.id.bt).setOnClickListener(v -> {
//            Bundle bundle = new Bundle();
//            bundle.putString(WebFragment.KEY_LINK, et.getText().toString());
//            ARouter.getInstance()
//                    .build("/web/WebActivity")
//                    .with(bundle)
//                    .navigation();

            Intent intent = new Intent(Intent.ACTION_VIEW);    //为Intent设置Action属性
            intent.setData(Uri.parse("https://www.baidu.com")); //为Intent设置DATA属性
            startActivity(intent);

        });
    }

}
