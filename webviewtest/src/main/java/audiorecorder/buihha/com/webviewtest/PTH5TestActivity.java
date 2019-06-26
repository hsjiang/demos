package audiorecorder.buihha.com.webviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by riven_chris on 2016/12/4.
 */

public class PTH5TestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvLoad;
    private TextView tvReload;
    private TextView tvStart;
    private WebView wv;
    private WebView wv2;
    private WebView wv3;

    private String url = "https://oa.putao.com/forms/detail?afid=36494&fid=64&linkfrom=forms_mytodo&linkMsgId=600257";
    private String url2 = "http://test.fe.ptdev.cn/ptwd/pages/growth.html?inapp=1&uid=60000042&token=a81a96c9723e427cb362c111fd108ed9&appid=1109&device_id=c8dc9dbcf21c756c1480588505308&cid=60002115&";
    private String url3 = "http://test.fe.ptdev.cn/ptwd/pages/paiband_sleep.html?inapp=1&uid=60000042&token=e4f31d11fc474c0aa21b398d46a99f7d&appid=1109&device_id=c8dc9dbcf21c756c1480588505308&cid=60003161&";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pt_h5_test);
        intView();
    }

    private void intView() {
        tvLoad = (TextView) findViewById(R.id.tv_load);
        tvReload = (TextView) findViewById(R.id.tv_reload);
        tvStart = (TextView) findViewById(R.id.tv_start);
        tvLoad.setOnClickListener(this);
        tvReload.setOnClickListener(this);
        tvStart.setOnClickListener(this);

        wv = (WebView) findViewById(R.id.wv);
        wv2 = (WebView) findViewById(R.id.wv2);
        wv3 = (WebView) findViewById(R.id.wv3);
        setSettings(wv);
        setSettings(wv2);
        setSettings(wv3);
    }

    private void setSettings(WebView wv) {
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        wv.setWebChromeClient(new WebChromeClient() {

        });
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(PTH5TestActivity.this, "onReceivedError: " + description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(PTH5TestActivity.this, "onReceivedError: " + error.getDescription(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                Toast.makeText(PTH5TestActivity.this, "onReceivedHttpError: " + request.getUrl() + " " + errorResponse.getStatusCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        wv.loadUrl(url);
//        wv2.loadUrl(url2);
//        wv3.loadUrl(url3);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_load:
                wv.loadUrl(url);
//                wv2.loadUrl(url2);
//                wv3.loadUrl(url3);
                break;
            case R.id.tv_reload:
                wv.reload();
//                wv2.reload();
//                wv3.reload();
                break;
            case R.id.tv_start:
                startActivity(new Intent(this, WeiXinH5Activity.class));
                break;
        }
    }
}
