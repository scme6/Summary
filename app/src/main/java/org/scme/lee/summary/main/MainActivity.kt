package org.scme.lee.summary.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.appus.splash.Splash
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.*
import org.scme.lee.summary.R
import org.scme.lee.summary.base.BaseActivity
import org.scme.lee.summary.main.other.OtherFragment
import org.scme.lee.summary.main.home.HomeFragment
import org.scme.lee.summary.main.personalcenter.PersonalCenterFragment

/**
 * Created by Lee on 2017/12/5.
 * 主activity
 */
class MainActivity : BaseActivity(), Toolbar.OnMenuItemClickListener {
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        val id = item?.itemId
        return when (id) {
            R.id.search -> {
                val intent = Intent(this@MainActivity, SearchActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.crop -> {
                val intent = Intent(this@MainActivity, ScanActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun toolBar(): Boolean {
        return true
    }

    private var homeFragment: HomeFragment? = null
    private var dashBoardFragment: OtherFragment? = null
    private var personalCenterFragment: PersonalCenterFragment? = null
    private var fragmentManager: FragmentManager? = null
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                initFragment(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                initFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_personal_center -> {
                initFragment(2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun layoutID() = R.layout.activity_main

    override fun inits(savedInstanceState: Bundle?) {
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)
        toolbar.setOnMenuItemClickListener(this@MainActivity)
        toolbar.visibility = View.INVISIBLE
        Splash.Builder(this@MainActivity, null).perform()
        toolbar.visibility = View.VISIBLE
        fragmentManager = supportFragmentManager
        if (savedInstanceState != null) {
            homeFragment = fragmentManager?.findFragmentByTag("home") as HomeFragment?
            dashBoardFragment = fragmentManager?.findFragmentByTag("dash") as OtherFragment?
            personalCenterFragment = fragmentManager?.findFragmentByTag("personal") as PersonalCenterFragment?
        }
    }

    override fun initView() {
        val nt = navigation as BottomNavigationViewEx
        nt.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener
        initFragment(0)
    }

    private fun initFragment(position: Int?) {
        val transaction = fragmentManager?.beginTransaction()
        if (homeFragment != null)
            transaction?.hide(homeFragment)
        if (dashBoardFragment != null)
            transaction?.hide(dashBoardFragment)
        if (personalCenterFragment != null)
            transaction?.hide(personalCenterFragment)
        when (position) {
            0 -> {
                if (homeFragment == null) {
                    homeFragment = HomeFragment()
                    transaction?.add(R.id.main_frame, homeFragment, "home")
                } else transaction?.show(homeFragment)
            }
            1 -> {
                if (dashBoardFragment == null) {
                    dashBoardFragment = OtherFragment()
                    transaction?.add(R.id.main_frame, dashBoardFragment, "dase")
                } else transaction?.show(dashBoardFragment)
            }
            2 -> {
                if (personalCenterFragment == null) {
                    personalCenterFragment = PersonalCenterFragment()
                    transaction?.add(R.id.main_frame, personalCenterFragment, "personal")
                } else transaction?.show(personalCenterFragment)
            }
        }
        transaction?.commitAllowingStateLoss()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}