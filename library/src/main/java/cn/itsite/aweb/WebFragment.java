package cn.itsite.aweb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.orhanobut.logger.Logger;

import cn.itsite.abase.BaseApp;
import cn.itsite.abase.mvp.view.base.BaseActivity;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * @author: leguang
 * @e-mail: langmanleguang@qq.com
 * @version: v0.0.0
 * @blog: https://github.com/leguang
 * @time: 2016/9/4 0004 11:50
 * @description: 负责项目中的web部分。
 */

@Route(path = "/web/WebFragment")
public class WebFragment extends BaseFragment {
    public static final String TAG = WebFragment.class.getSimpleName();
    public static final String KEY_LINK = "key_link";
    protected WebView webView;
    protected Toolbar toolbar;
    protected String link;
    protected ProgressBar progressBar;

    public static WebFragment getInstance(Bundle bundle) {
        WebFragment fragment = new WebFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            link = bundle.getString(KEY_LINK);
        }
        Log.d(TAG, "link-->" + link);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        toolbar = view.findViewById(R.id.toolbar_web_fragment);
        progressBar = view.findViewById(R.id.progressBar_web_fragment);
        webView = view.findViewById(R.id.webView_web_fragment);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initWebView();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
        toolbar.inflateMenu(R.menu.menu_web);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.close) {
                ((BaseActivity) _mActivity).onBackPressedSupport();
            } else if (item.getItemId() == R.id.share) {
                share(webView.getTitle(), webView.getUrl());
            }
            if (item.getItemId() == R.id.go2browser) {
                Uri uri = Uri.parse(webView.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(BaseApp.mContext.getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
            return true;
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        webView.clearCache(true);
        webView.clearHistory();

        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //webview自适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webView.addJavascriptInterface(new JsObject(), "android");

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                link = url;
                view.loadUrl(url);
                Logger.e("url-->" + url);

                // 如下方案可在非微信内部WebView的H5页面中调出微信支付
                if (url.startsWith("mol://pay?")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
//                return super.shouldOverrideUrlLoading(view, url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                link = url;
                String title = view.getTitle();
                if (toolbar != null) {
                    toolbar.setTitle(title);
                }

                if (progressBar.getVisibility() != View.INVISIBLE) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                Logger.e(" error.toString()-->" + error.toString());
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                if (newProgress > 80) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (toolbar != null) {
                    toolbar.setTitle(title);
                }
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, true);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });
        webView.loadUrl(link);
//        webView.loadUrl("file:///android_asset/url.html");
    }

    @Override
    public boolean onBackPressedSupport() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            ((BaseActivity) _mActivity).onBackPressedSupport();
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
            if (webView.getParent() != null) {
                ((ViewGroup) webView.getParent()).removeView(webView);
            }
            webView = null;
        }
    }

    public class JsObject {

        @JavascriptInterface
        public void action(String url, String title) {
            Log.d(TAG, "action: " + url + title);
        }
    }

    public void share(String title, String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        if (intent.resolveActivity(_mActivity.getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, title));
        }
    }
}
