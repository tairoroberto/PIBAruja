package br.com.trmasolutions.pibaruja.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import br.com.trmasolutions.pibaruja.R
import br.com.trmasolutions.pibaruja.ui.admin.AdminActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class HomeActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)

        val fragments: ArrayList<Fragment> = ArrayList()
        fragments.add(HomeFragment.newInstance("Eventos"))
        fragments.add(HomeFragment.newInstance("ECPC"))

        if (savedInstanceState == null) {
            mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, fragments)

            container.adapter = mSectionsPagerAdapter
            container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
            tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        lateinit var editUser : EditText
        lateinit var editPassword : EditText

        if (id == R.id.action_admin) {

            val dialogBuilder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_login, null)
            editUser = dialogView.findViewById(R.id.editUser)
            editPassword = dialogView.findViewById(R.id.editPassword)

            dialogBuilder.setView(dialogView)
            val alertDialog = dialogBuilder.create()
            alertDialog.setTitle("Atenção")
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "ENTRAR", { dialog, _ ->
                if (!editUser.text.isEmpty() && !editPassword.text.isEmpty() && editUser.text.toString() == "admin" && editPassword.text.toString() == "123456") {
                    startActivity<AdminActivity>()
                }else{
                    toast("Dados incorretos")
                }
                dialog.dismiss()
            })
            alertDialog.show()

            return false
        }

        return super.onOptionsItemSelected(item)
    }

    inner class SectionsPagerAdapter(fm: FragmentManager, private val fragments: ArrayList<Fragment>) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }
    }
}
