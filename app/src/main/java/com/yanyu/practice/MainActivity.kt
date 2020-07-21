package com.yanyu.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yanyu.practice.banner.activity.WelcomeActivity
import com.yanyu.practice.pictureselector.PictureMainActivity
import com.yanyu.practice.pictureselector.SimpleActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
}