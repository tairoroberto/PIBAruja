package br.com.trmasolutions.pibaruja.ui.home

import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.TabItem
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.Menu
import android.view.MenuItem

import br.com.trmasolutions.pibaruja.R
import kotlinx.android.synthetic.main.activity_home.*

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

        if (id == R.id.action_settings) {
            return true
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
