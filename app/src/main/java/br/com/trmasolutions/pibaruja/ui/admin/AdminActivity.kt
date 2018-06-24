package br.com.trmasolutions.pibaruja.ui.admin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.trmasolutions.pibaruja.R
import br.com.trmasolutions.pibaruja.ui.event.EventActivity
import kotlinx.android.synthetic.main.admin_activity.*
import org.jetbrains.anko.startActivity

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_activity)

        textViewEvents.setOnClickListener { startActivity<EventActivity>() }
        textViewNotifications.setOnClickListener {  }
        textViewVideos.setOnClickListener {  }
    }

}
