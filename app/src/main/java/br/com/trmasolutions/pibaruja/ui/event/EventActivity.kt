package br.com.trmasolutions.pibaruja.ui.event

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.trmasolutions.pibaruja.R

class EventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, EventFragment.newInstance())
                    .commitNow()
        }
    }

}
