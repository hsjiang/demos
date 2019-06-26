package audiorecorder.buihha.com.webviewtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by riven_chris on 2016/12/4.
 */

public class PTh5ViewPagerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvLoad;
    private TextView tvReload;
    private TextView tvStart;
    private ViewPager viewPager;
    private String[] strings = new String[]{
            "http://test.fe.ptdev.cn//ptwd/pages/paibot_apps.html?inapp=1&uid=60000042&token=a81a96c9723e427cb362c111fd108ed9&appid=1109&device_id=c8dc9dbcf21c756c1480588505308&cid=60002115&",
            "http://test.fe.ptdev.cn/ptwd/pages/growth.html?inapp=1&uid=60000042&token=a81a96c9723e427cb362c111fd108ed9&appid=1109&device_id=c8dc9dbcf21c756c1480588505308&cid=60002115&",
            "http://test.fe.ptdev.cn/ptwd/pages/paiband_sleep.html?inapp=1&uid=60000042&token=e4f31d11fc474c0aa21b398d46a99f7d&appid=1109&device_id=c8dc9dbcf21c756c1480588505308&cid=60003161&",
    };
    private List<String> urls;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pt_h5_viewpager);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.getAdapter().notifyDataSetChanged();
    }

    private void initView() {
        tvLoad = (TextView) findViewById(R.id.tv_load);
        tvReload = (TextView) findViewById(R.id.tv_reload);
        tvStart = (TextView) findViewById(R.id.tv_start);
        tvLoad.setOnClickListener(this);
        tvReload.setOnClickListener(this);
        tvStart.setOnClickListener(this);

        urls = new ArrayList<>();
        urls.addAll(Arrays.asList(strings));

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyPagerAdapter adapter = new MyPagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        adapter.setData(urls);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_load:
                viewPager.getAdapter().notifyDataSetChanged();
                break;
            case R.id.tv_reload:
                viewPager.getAdapter().notifyDataSetChanged();
                break;
            case R.id.tv_start:
                startActivity(new Intent(this, WeiXinH5Activity.class));
                break;
        }
    }

    private class MyPagerAdapter extends PagerAdapter {
        private List<String> charts = new ArrayList<>();
        private Context context;

        public MyPagerAdapter(Context context) {
            this.context = context;
        }

        public void setData(List<String> charts) {
            this.charts.clear();
            if (charts != null && !charts.isEmpty()) {
                this.charts.addAll(charts);
            } else {
                this.charts.add("");
            }
            notifyDataSetChanged();
        }

        public void clearAll() {
            this.charts.clear();
            notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            Log.d("MyPagerAdapter", "MyPagerAdapter: " + MyPagerAdapter.this
                    + "\ncharts: " + charts + ", " + charts.size());
            return charts.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_pt_h5_viewpager_item, null);
            WebView wvChart = (WebView) view.findViewById(R.id.wv_chart);
            setSettings(wvChart);
            if (charts != null && !charts.isEmpty()) {
                wvChart.loadUrl(charts.get(position));
            }
            container.addView(view);
            return view;
        }

        private void setSettings(WebView wv) {
            wv.getSettings().setJavaScriptEnabled(true);
            wv.getSettings().setAppCacheEnabled(true);
            wv.getSettings().setDatabaseEnabled(true);
            wv.setWebChromeClient(new WebChromeClient());
            wv.setWebChromeClient(new WebChromeClient());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
