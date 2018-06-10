package br.com.trmasolutions.ecpcaruja.ui.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.trmasolutions.ecpcaruja.BuildConfig
import br.com.trmasolutions.ecpcaruja.R
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.google.android.instantapps.InstantApps
import com.google.firebase.messaging.FirebaseMessaging
import io.fabric.sdk.android.Fabric
import br.com.trmasolutions.ecpcaruja.ui.home.HomeActivity
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up Crashlytics, disabled for debug builds
        val crashlyticsKit = Crashlytics.Builder()
                .core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .build()

        // Initialize Fabric with the debug-disabled crashlytics.
        Fabric.with(this, crashlyticsKit)
        Crashlytics.setBool("InstantApp", InstantApps.isInstantApp(this))
        setContentView(R.layout.activity_splash)

        FirebaseMessaging.getInstance().subscribeToTopic("ecpc")

        Timer().schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    jump()
                }
            }
        }, 2500)
    }

    private fun jump() {
        if (isFinishing) {
            return
        }
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
