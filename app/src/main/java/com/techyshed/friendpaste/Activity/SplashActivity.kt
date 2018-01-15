package com.techyshed.friendpaste.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.techyshed.friendpaste.MainActivity
import com.techyshed.friendpaste.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar!!.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        var h = Handler()
        h.postDelayed(Runnable {
            var i  = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        },2500)

    }
}
