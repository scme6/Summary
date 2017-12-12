package org.scme.lee.summary

import android.widget.Toast
import com.mob.MobApplication
import com.mob.MobSDK
import com.tencent.smtt.sdk.QbSdk

/**
 * Created by Lee on 2017/11/22.
 */
class BaseApplication : MobApplication() {
    var appLitactionContext: BaseApplication? = null
    var toast: Toast? = null
    override fun onCreate() {
        super.onCreate()
        appLitactionContext = this
        MobSDK.init(this@BaseApplication)
        QbSdk.initX5Environment(this@BaseApplication,null)
    }

    fun Toast(msg: String) {
        if (toast == null) {
            toast = Toast.makeText(appLitactionContext, msg, Toast.LENGTH_SHORT)
            toast?.show()
        } else {
            toast?.setText(msg)
            toast?.show()
        }
    }

}