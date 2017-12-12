package org.scme.lee.summary.main

import android.os.Bundle
import org.scme.lee.summary.R
import org.scme.lee.summary.base.BaseActivity

/**
 * Created by Lee on 2017/12/5.
 * 搜索
 */
class SearchActivity : BaseActivity() {
    override fun toolBar() = false

    override fun layoutID(): Int = R.layout.activity_search

    override fun inits(savedInstanceState: Bundle?) {
    }

    override fun initView() {
    }
}