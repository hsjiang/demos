package com.example.riven_chris.demos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    WebView webView;
    GradientTextView gtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
//        Glide.init(this, new GlideBuilder());
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.web_view);
        gtv = (GradientTextView) findViewById(R.id.gtv);
        gtv.onTime(0, "现在还剩下100%", false);
        final CountDownTimer countDownTimer = new CountDownTimer(50000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                float percent = millisUntilFinished / 50000.f;
                String text = "现在还剩下" + (int) (percent * 100) + "%了";
                gtv.onTime(1 - percent, text, true);
            }

            @Override
            public void onFinish() {
                gtv.onTime(0, "现在玩完啦", false);
            }
        };

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                countDownTimer.start();
            }
        }, 5000);

        gtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

//        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        Log.d("TAG", "memory: " + manager.getMemoryClass() + " runtime: " + (Runtime.getRuntime().maxMemory() / (1024 * 1024)));
//
//        tv = (TextView) findViewById(R.id.tv);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(MainActivity.this, SecondActivity.class);
//                Intent intent2 = new Intent(MainActivity.this, ThirdActivity.class);
//                startActivities(new Intent[]{intent1, intent2});
//            }
//        });
//
//        Fresco.getImagePipeline();
//        OkHttpClient okHttpClient = null;
//        okHttpClient.cancel("");

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//开启对JavaScript的支持
        settings.setDefaultTextEncodingName("UTF-8");//设置字符编码

        settings.setSupportZoom(true);
        // 开启alert
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 开启按钮按下显示
        settings.setLightTouchEnabled(true);
        settings.setUseWideViewPort(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {

        });
        String url = "http://paibot.fe.ptdev.cn/browser/?uid=6079814&token=bee5697b65194d9eadd042f0441b60ac&appid=1109&cid={\"uidlist\":[{\"name\":\"11\",\"uid\":\"6083504\",\"avatar\":\"\"},{\"name\":\"22\",\"uid\":\"6083692\",\"avatar\":\"\"}]}#!/loading?type=applicationmanage";
        webView.loadUrl(url);
    }
}
 