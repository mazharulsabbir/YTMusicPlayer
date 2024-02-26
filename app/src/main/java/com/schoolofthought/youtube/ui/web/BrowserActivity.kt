package com.schoolofthought.youtube.ui.web

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.schoolofthought.youtube.R
import com.schoolofthought.youtube.video_detail.SingleVideoActivity

class BrowserActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var actionButton: FloatingActionButton
    private var latestUrl: String? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
        webView = findViewById(R.id.webPlayerView)
        actionButton = findViewById(R.id.openBackgroundPlayer)

        webView.apply {
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.domStorageEnabled = true
            settings.setSupportMultipleWindows(true)

            loadUrl("https://m.youtube.com")

            webChromeClient = YTWebChromeClient()
            webViewClient = YTWebViewClient(object : UrlChangeListener {
                override fun onUrlChange(url: String) {
                    latestUrl = url
                    if (latestUrl?.contains("watch?v=") == true) {
                        actionButton.show()
                    } else {
                        actionButton.hide()
                    }
                }
            })
        }

        val indent = Intent(this@BrowserActivity, SingleVideoActivity::class.java)

        actionButton.setOnClickListener {
            latestUrl?.let { url ->
                indent.apply {
                    putExtra(SingleVideoActivity.EXTRA_VIDEO_URL, url)
                }
                startActivity(indent)
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        webView.apply {
            if (canGoBack()) {
                goBack()
                return
            }
        }
        super.onBackPressed()
    }
}