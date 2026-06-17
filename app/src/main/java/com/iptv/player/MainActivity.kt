package com.iptv.player

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowManager
import android.webkit.*
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        webView = WebView(this)
        setContentView(webView)
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            mediaPlaybackRequiresUserGesture = false
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            cacheMode = WebSettings.LOAD_NO_CACHE
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
            useWideViewPort = true
            loadWithOverviewMode = true
        }
        webView.setLayerType(android.view.View.LAYER_TYPE_HARDWARE, null)
        webView.webChromeClient = object : WebChromeClient() {}
        webView.webViewClient = WebViewClient()
        webView.loadUrl("file:///android_asset/index.html")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack(); return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onPause()   { super.onPause();   webView.onPause() }
    override fun onResume()  { super.onResume();  webView.onResume() }
    override fun onDestroy() { webView.destroy(); super.onDestroy() }
}
