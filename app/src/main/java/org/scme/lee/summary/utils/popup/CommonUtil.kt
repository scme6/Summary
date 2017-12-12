package org.scme.lee.summary.utils.popup


import android.view.View

/**
 * Created by Lee on 2017/12/7.
 */

object CommonUtil {
    /**
     * 测量View的宽高
     *
     * @param view View
     */
    fun measureWidthAndHeight(view: View) {
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(widthMeasureSpec, heightMeasureSpec)
    }

}