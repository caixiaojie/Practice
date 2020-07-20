package com.yanyu.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yanyu.practice.pictureselector.PictureMainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * 图片选择
     */
    fun startPictureSelector(view: View) {
        startActivity(Intent(this,PictureMainActivity :: class.java))
    }
}