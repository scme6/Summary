package org.scme.lee.summary.base

import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.scme.lee.summary.BaseApplication

/**
 * Created by Lee on 2017/11/16.
 */
abstract class BaseFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(layoutID(), container, false) as View

//        val viewGroup = rootView.parent as ViewGroup
//        if (viewGroup != null)
//            viewGroup?.removeView(rootView)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    abstract fun layoutID(): Int
    abstract fun initView()

}