package org.scme.lee.summary.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import org.scme.lee.summary.R
import org.scme.lee.summary.base.BaseActivity
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.text.TextUtils
import android.webkit.WebResourceRequest
import cn.sharesdk.tencent.qq.QQ
import com.tencent.smtt.sdk.*
import kotlinx.android.synthetic.main.activity_webview.*
import org.scme.lee.summary.utils.Share
import android.opengl.ETC1.getHeight
import android.support.v4.widget.PopupWindowCompat.showAsDropDown
import android.view.*
import org.scme.lee.summary.utils.popup.CommonPopupWindow


/**
 * Created by Lee on 2017/12/7.
 * 公用WebViewActivity
 */
class WebViewActivity : BaseActivity(), CommonPopupWindow.ViewInterface {
    override fun getChildView(view: View, layoutResId: Int) {
    }

    override fun toolBar() = false

    override fun layoutID() = R.layout.activity_webview

    override fun inits(savedInstanceState: Bundle?) {
    }

    override fun initView() {
        val title = intent.getStringExtra("title")
        val url = intent.getStringExtra("url")
        supportActionBar?.title = title
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)
        webviewSetting(url)
    }

    /**
     * 让webview属性设置
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun webviewSetting(url: String?) {
        val webSetting = webview.settings
        webSetting.allowFileAccess = true
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSetting.setSupportZoom(true)
        webSetting.builtInZoomControls = true
        webSetting.useWideViewPort = true
        webSetting.setSupportMultipleWindows(false)
        webSetting.setAppCacheEnabled(true)
        webSetting.domStorageEnabled = true
        webSetting.javaScriptEnabled = true
        webSetting.setGeolocationEnabled(true)
        webSetting.setAppCacheMaxSize(java.lang.Long.MAX_VALUE)
        webSetting.setAppCachePath(this.getDir("appcache", 0).path)
        webSetting.databasePath = this.getDir("databases", 0).path
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0).path)
        webSetting.pluginState = WebSettings.PluginState.ON_DEMAND
        CookieSyncManager.createInstance(this)
        CookieSyncManager.getInstance().sync()
        webview.loadUrl(url)
        webview.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(p0: WebView?, p1: String?): Boolean {
                return false
            }
        })
        webview.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress == 100) {
                    myProgressBar.visibility = View.GONE
                } else {
                    if (View.INVISIBLE === myProgressBar.visibility) {
                        myProgressBar.visibility = View.VISIBLE
                    }
                    myProgressBar.progress = newProgress
                }
                super.onProgressChanged(view, newProgress)
            }
        })
    }

    /**
     * 设置返回键不退出activity ，
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return if (webview != null && webview.canGoBack()) {
                webview.goBack()
                true
            } else
                super.onKeyDown(keyCode, event)
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_webview, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.refresh -> {
                webview.reload()
                true
            }
            R.id.share -> {
//                Share().share(QQ.NAME)
                showSharePopup()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * 弹起分享的popupWindow  453
     */
    private var popupWindow: CommonPopupWindow? = null

    fun showSharePopup() {
        if (popupWindow != null && popupWindow!!.isShowing)
            return
        popupWindow = CommonPopupWindow.Builder(this)
                .setView(R.layout.layout_share_pupup)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setViewOnclickListener(this@WebViewActivity)
                .create()
        popupWindow?.showAtLocation(frameLayout, Gravity.BOTTOM, 0, 0)
    }

}