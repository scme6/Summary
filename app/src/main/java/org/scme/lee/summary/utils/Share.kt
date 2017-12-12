package org.scme.lee.summary.utils

import android.util.Log
import cn.sharesdk.framework.Platform
import cn.sharesdk.framework.PlatformActionListener
import cn.sharesdk.framework.ShareSDK
import java.util.*


/**
 * Created by Lee on 2017/12/7.
 */
class Share {

    fun share(platform: String) {
        var sp = Platform.ShareParams()
        sp.title = "github/scme6"
        sp.titleUrl = "https://github.com/scme6" // 标题的超链接
        sp.text = "github地址"
        sp.imageUrl = "http://chuantu.biz/t6/169/1512637405x-1566657960.png"
        sp.site = "github"
        sp.siteUrl = "https://github.com/scme6"
        val plat = ShareSDK.getPlatform(platform)
        plat.platformActionListener = object : PlatformActionListener {
            override fun onError(arg0: Platform, arg1: Int, arg2: Throwable) {
                Log.e("share", arg0.name + arg1 + arg2.message)
            }

            override fun onComplete(arg0: Platform, arg1: Int, arg2: HashMap<String, Any>) {
                Log.e("share", arg0.name + arg1 + arg2.values)
            }

            override fun onCancel(arg0: Platform, arg1: Int) {
                Log.e("share", arg0.name + arg1)
            }
        }
        plat.share(sp)
    }


}