package com.techyshed.friendpaste.Activity

import android.app.ProgressDialog
import android.content.ContentValues
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import com.techyshed.friendpaste.Database.DbManager
import com.techyshed.friendpaste.POJO.MyWebViewClient
import com.techyshed.friendpaste.R
import com.techyshed.volleyk.APIController
import com.techyshed.volleyk.ServiceVolley
import kotlinx.android.synthetic.main.activity_view.*
import org.json.JSONObject

class ViewActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_view)

        var b:Bundle = intent.extras
        var id = b.getInt("id")
        var title = b.getString("title")
        var des = b.getString("des")
        var url = b.getString("url")

        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        var webViewClient = MyWebViewClient()
        webView.setWebViewClient(webViewClient)

        webView.loadUrl(url)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack()
            return true
        }

        return super.onKeyDown(keyCode, event);
    }
}
