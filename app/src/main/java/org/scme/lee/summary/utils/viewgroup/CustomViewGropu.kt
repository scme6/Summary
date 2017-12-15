package org.scme.lee.summary.utils.viewgroup

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.view.ViewGroupCompat
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import cn.sharesdk.wechat.utils.i


/**
 * Created by Lee on 2017/12/14.
 */
class CustomViewGropu(private val con: Context?) : ViewGroup(con) {
    /**
     * 返回MarginLayoutParams
     */
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(con, attrs)
    }

    /**
     * 计算所有ChildView的宽度和高度 然后根据ChildView的计算结果，设置自己的宽和高
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        /**
         *获取该ViewGroup父容器为其设置的计算模式和尺寸
         */
        var measureWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        var measureHeightMode = MeasureSpec.getMode(heightMeasureSpec)
        var measureWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        var measureHeightSize = MeasureSpec.getSize(heightMeasureSpec)

        /**
         *计算出所有childView的宽高
         */
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        /**
         * 如果wrap_content 返回这个大小
         */
        var width = 0
        var height = 0

        /**
         *child 数量
         */
        val count = childCount
        var cWidth = 0
        var cHeight = 0
        var cParams: ViewGroup.MarginLayoutParams? = null


        /** *用于计算左边两个childView的高度*/
        var lHeight = 0
        /** *用于计算右边两个childView的高度，最终高度取二者之间大值*/
        var rHeight = 0
        /** *用于计算上边两个childView的宽度 */
        var tWidth = 0
        /** * 用于计算下面两个childiew的宽度，最终宽度取二者之间大值*/
        var bWidth = 0
        /** 遍历取出 */
        for (i in count until 1) {
            val childView = getChildAt(i)
            cWidth = childView.measuredWidth
            cHeight = childView.measuredHeight
            cParams = childView.layoutParams as ViewGroup.MarginLayoutParams
            // 上面两个childView
            if (i === 0 || i === 1) {
                tWidth += cWidth + cParams.leftMargin + cParams.rightMargin
            }
            // 下面两个childView
            if (i === 2 || i === 3) {
                bWidth += cWidth + cParams.leftMargin + cParams.rightMargin
            }
            // 左面两个childView
            if (i === 0 || i === 2) {
                lHeight += cHeight + cParams.topMargin + cParams.bottomMargin
            }
            // 右面两个childView
            if (i === 1 || i === 3) {
                rHeight += cHeight + cParams.topMargin + cParams.bottomMargin
            }
        }
        width = Math.max(tWidth, bWidth)
        height = Math.max(lHeight, rHeight)

        /**
         * 如果是wrap_content设置为我们计算的值
         * 否则：直接设置为父容器计算的值
         */
        setMeasuredDimension(if (measureWidthMode == MeasureSpec.EXACTLY) measureWidthSize else width, if (measureHeightMode == MeasureSpec.EXACTLY) measureHeightSize else height)

    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        val cCount = childCount
        var cWidth = 0
        var cHeight = 0
        var cParams: ViewGroup.MarginLayoutParams? = null
        /**
         * 遍历所有childView根据其宽和高，以及margin进行布局
         */
        for (i in 0 until cCount) {
            val childView = getChildAt(i)
            cWidth = childView.measuredWidth
            cHeight = childView.measuredHeight
            cParams = childView.layoutParams as ViewGroup.MarginLayoutParams
            var cl = 0
            var ct = 0
            var cr = 0
            var cb = 0
            when (i) {
                0 -> {
                    cl = cParams.leftMargin
                    ct = cParams.topMargin
                }
                1 -> {
                    cl = (width - cWidth - cParams.leftMargin
                            - cParams.rightMargin)
                    ct = cParams.topMargin
                }
                2 -> {
                    cl = cParams.leftMargin
                    ct = height - cHeight - cParams.bottomMargin
                }
                3 -> {
                    cl = (width - cWidth - cParams.leftMargin
                            - cParams.rightMargin)
                    ct = height - cHeight - cParams.bottomMargin
                }
            }
            cr = cl + cWidth
            cb = cHeight + ct
            childView.layout(cl, ct, cr, cb)
        }
    }


}