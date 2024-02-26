package com.schoolofthought.youtube.ui.web

import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient

class YTWebViewClient(private val urlChangeListener: UrlChangeListener?): WebViewClient() {
    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        Log.d(TAG, "doUpdateVisitedHistory: $url, $isReload")
        urlChangeListener?.onUrlChange(url ?: "")
        super.doUpdateVisitedHistory(view, url, isReload)
    }

    companion object {
        private const val TAG = "YTWebViewClient"
    }
}