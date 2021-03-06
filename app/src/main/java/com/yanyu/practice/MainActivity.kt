package com.yanyu.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yanyu.practice.banner.activity.WelcomeActivity
import com.yanyu.practice.materialdialog.MaterialDialogMainActivity
import com.yanyu.practice.materialdialog.toast
import com.yanyu.practice.permission.PermissionMainActivity
import com.yanyu.practice.pickerview.PickerViewMainActivity
import com.yanyu.practice.pictureselector.PictureMainActivity
import com.yanyu.practice.pictureselector.SimpleActivity
import com.yanyu.practice.shapeimage.ShapeImageActivity
import com.yanyu.practice.update.activity.UpdateMainActivity
import com.yanyu.practice.view.AgreeCheckBox
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAgreeCheckBox.setClickSpanCallback(object : AgreeCheckBox.ClickSpanCallback{
            override fun click(type: Int) {
                if (type == 1) {
                    toast("用户协议")
                }else {
                    toast("隐私协议")
                }
            }
        })
    }

    /**
     * 图片选择
     */
    fun startPictureSelector(view: View) {
        startActivity(Intent(this,SimpleActivity :: class.java))
    }

    /**
     * 轮播图
     */
    fun startBanner(view: View) {
        startActivity(Intent(this,WelcomeActivity :: class.java))
    }

    /**
     * 弹窗
     */
    fun startDialog(view: View) {
        startActivity(Intent(this,MaterialDialogMainActivity :: class.java))
    }

    /**
     * 权限
     */
    fun startPermissionX(view: View) {
        startActivity(Intent(this,PermissionMainActivity :: class.java))
    }

    /**
     * 选择器
     */
    fun startPickerView(view: View) {
        startActivity(Intent(this,PickerViewMainActivity :: class.java))
    }

    /**
     * 版本更新
     */
    fun startUpdate(view: View) {
        startActivity(Intent(this,UpdateMainActivity :: class.java))
    }

    /**
     * ShapeableImageView
     */
    fun startShapeableImageView(view: View) {
        startActivity(Intent(this,ShapeImageActivity :: class.java))
    }


}