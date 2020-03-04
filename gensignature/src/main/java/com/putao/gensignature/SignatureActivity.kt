package com.putao.gensignature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signature.*

class SignatureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signature)

        tvGet.setOnClickListener {
            val signature = SignatureUtil.getMd5Sign(this, etPackage.text.toString())
            tvSignature.text = signature
        }
    }
}
