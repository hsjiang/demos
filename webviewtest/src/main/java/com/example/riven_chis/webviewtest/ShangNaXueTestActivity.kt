package com.example.riven_chis.webviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import audiorecorder.buihha.com.webviewtest.R
import kotlinx.android.synthetic.main.activity_weixin_h5.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class ShangNaXueTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shang_na_xue_test)

        lifecycleScope.launchWhenResumed {
            val result = withContext(Dispatchers.IO) {
                (URL("https://cms.51xuetang.com/m/article/getArticle?articleId=1272166871333892097").readText())
            }
            val jsonObject = JSONObject(result)
            val htmlData = jsonObject.getString("content")
            webview.loadData(htmlData, "text/html", "UTF-8")
        }
    }
}
