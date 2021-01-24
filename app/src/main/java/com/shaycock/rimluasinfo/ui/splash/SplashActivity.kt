package com.shaycock.rimluasinfo.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.shaycock.rimluasinfo.R
import com.shaycock.rimluasinfo.ui.main.MainActivity

/**
 * Basic splash screen that loads the main activity after 2 seconds
 */
class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        //Load the main activity after 2 seconds
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 2000)
    }
}