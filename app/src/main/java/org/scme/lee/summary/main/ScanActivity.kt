package org.scme.lee.summary.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import cn.bingoogolapple.qrcode.core.QRCodeView
import kotlinx.android.synthetic.main.activity_main_scan.*
import org.scme.lee.summary.R
import org.scme.lee.summary.base.BaseActivity

@Suppress("DEPRECATION")
/**
 * Created by Lee on 2017/12/5.
 * 扫描
 */
class ScanActivity : BaseActivity(), QRCodeView.Delegate {
    override fun toolBar() = false

    override fun onStart() {
        super.onStart()
        zxingview.startCamera()
        zxingview.showScanRect()
    }

    override fun onStop() {
        zxingview.stopCamera()
        super.onStop()
    }

    /**
     * 扫描成功处理
     */
    override fun onScanQRCodeSuccess(result: String?) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        vibrate()
        zxingview.startSpot()
    }

    @SuppressLint("MissingPermission")
    fun vibrate() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(200)
    }

    /**
     * 打开相机失败
     */
    override fun onScanQRCodeOpenCameraError() {
        Toast.makeText(this@ScanActivity, "打开相机失败", Toast.LENGTH_SHORT)
    }

    override fun layoutID(): Int = R.layout.activity_main_scan

    override fun inits(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        zxingview.setDelegate(this@ScanActivity)
        zxingview.startSpot() //开始扫描       每个start方法 都对应有stop方法
        zxingview.showScanRect()//显示扫描框

    }

    override fun initView() {
    }

     fun onClick(view: View) {
        when (view.id) {
            R.id.scan_qr_code -> {
                //扫描二维码，并改变扫描边框样式
                zxingview.changeToScanQRCodeStyle()
            }
            R.id.scan_barcode -> {
                //扫描条形码，并改变扫描边框样式
                zxingview.changeToScanBarcodeStyle()
            }
            R.id.open_flashlight -> {
                //打开闪光灯，夜间使用
                zxingview.openFlashlight()
            }
            R.id.close_flashlight -> {
                //关闭闪光灯
                zxingview.closeFlashlight()
            }
        }
    }

    override fun onDestroy() {
        zxingview.onDestroy()
        super.onDestroy()
    }

}