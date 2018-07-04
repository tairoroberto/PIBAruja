package br.com.trmasolutions.pibaruja

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.facebook.stetho.Stetho

/**
 * Created by tairo on 11/10/17 12:06 AM.
 */
open class CustomApplication : Application() {

    companion object {
        private val TAG = "RemoteJobs"
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}
