package com.example.riven_chis.webviewtest;

import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import audiorecorder.buihha.com.webviewtest.R;

public class WeiXinH5Activity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin_h5);

        webview = (WebView) findViewById(R.id.webview);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);//开启对JavaScript的支持
        settings.setDefaultTextEncodingName("UTF-8");//设置字符编码

        settings.setSupportZoom(true);
        // 开启alert
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 开启按钮按下显示
        settings.setLightTouchEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
//        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        webview.addJavascriptInterface(new OpenApp(), "openApp");
        webview.setWebChromeClient(new WebChromeClient() {

        });
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("putao")) {
//                    webview.loadUrl("javascript:callFunction('{\"data\":{\"device_id\":\"P4ABCJ130039\",\"status\":1,\"_request_id\":\"00000000-0000-0000-0000-000000000004\"},\"type\":\"bluetooth-status\"}')");
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webview.loadUrl("http://dev.fe.ptdev.cn/pt_report/index.html?uid=60012152&appid=8101&token=yPyeyGJuJQGDyAGGPsDueKtlNysKGGPPQQeuPPytPGPPQQllPPNJuJPPQQPlPPAlQNPPQQulPPPleGPPPPGoPPPPtseAullGPPPPePyAwJGsuQPPwGwQtPPloyPPouKw&uid_children=60012191&pico_device_id=pico-P1BGCK040073-1&reportTime=1498563771.0");
    }


    class OpenApp {
        public void open(final String pkgName) {
            new Handler().post(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    startActivity(getApplicationContext().getPackageManager().getLaunchIntentForPackage(pkgName));
                }
            });
        }
    }
}
