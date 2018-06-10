package br.com.trmasolutions.pibaruja.ui.admin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.trmasolutions.pibaruja.R
import br.com.trmasolutions.pibaruja.ui.admin.ui.admin.AdminFragment

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, AdminFragment.newInstance())
                    .commitNow()
        }
    }

}
