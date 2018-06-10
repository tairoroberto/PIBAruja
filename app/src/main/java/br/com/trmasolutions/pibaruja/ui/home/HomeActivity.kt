package br.com.trmasolutions.pibaruja.ui.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.trmasolutions.pibaruja.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance())
                    .commitNow()
        }
    }

}
