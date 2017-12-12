package org.scme.lee.summary.base

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

/**
 * Created by Lee on 2017/11/16.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!toolBar())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                supportActionBar?.elevation = 0f
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        setContentView(layoutID())
        inits(savedInstanceState)
        initView()
    }

    /**
     * 布局
     */
    abstract fun toolBar(): Boolean

    /**
     * 布局
     */
    abstract fun layoutID(): Int


    /**
     * 初始化属性
     */
    abstract fun inits(savedInstanceState: Bundle?)

    /**
     * 初始化view
     */
    abstract fun initView()

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}