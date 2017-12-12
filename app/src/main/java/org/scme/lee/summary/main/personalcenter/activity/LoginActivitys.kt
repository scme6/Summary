package org.scme.lee.summary.main.personalcenter.activity

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import cn.sharesdk.framework.Platform
import cn.sharesdk.framework.PlatformActionListener
import cn.sharesdk.framework.ShareSDK
import cn.sharesdk.tencent.qq.QQ
import kotlinx.android.synthetic.main.activity_login.*
import org.scme.lee.summary.R
import org.scme.lee.summary.base.BaseActivity
import java.util.*

/**
 * Created by Lee on 2017/11/16.
 * 登录
 */
class LoginActivitys : BaseActivity(), PlatformActionListener {
    override fun onComplete(p0: Platform?, p1: Int, p2: HashMap<String, Any>?) {
        Log.e(p0?.name + ":" + "onComplete", p2.toString())
        when (p0?.name) {
            QQ.NAME -> {
                logo.setImageURI(Uri.parse(p2?.get("figureurl_qq_2").toString()))
                email.setText(p2?.get("nickname").toString())
                Log.e("onComplete", p2.toString())
                p2?.forEach {
                    Log.e(it.key, it.value.toString())
                }
            }
        }

    }

    override fun onCancel(p0: Platform?, p1: Int) {
    }

    override fun onError(p0: Platform?, p1: Int, p2: Throwable?) {
    }

    override fun toolBar() = false

    fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.email_sign_in_button -> {
                var focusView: View? = null
                var cancel = false
                email.error = null
                password.error = null
                if (password.text.toString().trim().isNullOrEmpty()) {
                    password.error = getString(R.string.error_field_required)
                    cancel = true
                    focusView = password
                }
                if (password.text.toString().length < 6) {
                    focusView = password
                    password.error = getString(R.string.error_invalid_password)
                    cancel = true
                }
                if (email.text.toString().trim().isNullOrEmpty()) {
                    email.error = getString(R.string.error_field_required)
                    cancel = true
                    focusView = email
                }
                if (!email.text.toString().contains("@")) {
                    email.error = getString(R.string.error_invalid_email)
                    focusView = email
                    cancel = true
                }
                if (cancel) {
                    focusView?.requestFocus()
                } else
                    finish()
            }
            R.id.login_qq -> {
                showLogin("QQ")
            }
            R.id.login_sina -> {
                showLogin("SinaWeibo")
            }
            R.id.login_wechat -> {
                showLogin("Wechat")
            }
            R.id.login_facebook -> {
                showLogin("Facebook")
            }
            R.id.logo -> {
                //使用动画实现头像控件抖动的效果
                val anim = TranslateAnimation(0f, 10f, 0f, 0f)
                // 利用 CycleInterpolator 参数 为float 的数  表示 抖动的次数，而抖动的快慢是由 duration 和 CycleInterpolator 的参数的大小 联合确定的
                anim.interpolator = CycleInterpolator(2f)
                anim.duration = 300
                logo.startAnimation(anim)
            }
        }
    }

    override fun layoutID(): Int = R.layout.activity_login

    override fun inits(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initView() {
    }

    fun showLogin(palatform: String) {
        val plat = ShareSDK.getPlatform(palatform)
        if (plat.isAuthValid) {
            plat.removeAccount(true)
//            var userId = plat.db.userId
//            if (!TextUtils.isEmpty(userId)) {
////                        UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
//                //登录
//                return
//            }
        }
        plat.platformActionListener = this@LoginActivitys
        //true不使用SSO授权，false使用SSO授权
        plat.SSOSetting(false)
        plat.showUser(null)
    }
}