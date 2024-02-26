package com.schoolofthought.youtube.ui.web

import android.view.View
import android.webkit.WebChromeClient

class YTWebChromeClient: WebChromeClient() {
    override fun getVideoLoadingProgressView(): View? {
        return super.getVideoLoadingProgressView()
    }
}