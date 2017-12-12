package org.scme.lee.summary.main.home

import android.view.View
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import org.scme.lee.summary.R
import org.scme.lee.summary.base.BaseFragment
import org.scme.lee.summary.loader.GlideImageLoader

/**
 * Created by Lee on 2017/11/16.
 * 首页
 */
class HomeFragment : BaseFragment() {
    override fun layoutID() = R.layout.fragment_home

    override fun initView() {
        setBanner()
    }

    fun setBanner() {
        banner.setDelayTime(2000)
        val imageList = listOf<Int>(R.mipmap.banner1, R.mipmap.banner1, R.mipmap.banner1)
        banner.setImages(imageList)
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR)
        banner.setIndicatorGravity(BannerConfig.RIGHT)
        banner.setBannerAnimation(Transformer.Accordion)
        banner.setImageLoader(GlideImageLoader())
        banner.start()
    }

    override fun onStart() {
        super.onStart()
        banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        banner.stopAutoPlay()
    }

}