package org.scme.lee.summary.main.personalcenter

import android.content.Intent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_personal_center.*
import org.scme.lee.summary.BaseApplication
import org.scme.lee.summary.R
import org.scme.lee.summary.base.BaseFragment
import org.scme.lee.summary.main.WebViewActivity
import org.scme.lee.summary.main.personalcenter.activity.LoginActivitys

/**
 * Created by Lee on 2017/11/16.
 * 个人中心
 */
class PersonalCenterFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.card1 -> startActivity(Intent(this@PersonalCenterFragment.activity, LoginActivitys::class.java))
            R.id.card2 -> {
                var intent = Intent(this@PersonalCenterFragment.activity, WebViewActivity::class.java)
                intent.putExtra("title", "关于我")
                intent.putExtra("url", "https://github.com/scme6")
                startActivity(intent)
            }
        }
    }

    var mContext: BaseApplication? = null

    init {
        mContext = BaseApplication()
    }

    override fun layoutID() = R.layout.fragment_personal_center

    override fun initView() {
        pzv.setIsParallax(true)    //允许视差动画
        pzv.setIsZoomEnable(true)  //允许头部放大
        pzv.setSensitive(1.5f)     //敏感度1.5
        pzv.setZoomTime(500)      //头部缩放时间500毫秒
        initListener()
    }

    private fun initListener() {
        card1.setOnClickListener(this@PersonalCenterFragment)
        card2.setOnClickListener(this@PersonalCenterFragment)
    }


}