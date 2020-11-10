package com.yanyu.practice.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.yanyu.practice.R
import kotlinx.android.synthetic.main.item_set_layout.view.*

/**
 * Created by 二哈 on 2020/11/10.
 */
class SettingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var leftTxt: String?= ""
    private var rightTxt: String? = ""
    private var left_icon: Int = R.mipmap.ic_launcher
    private var right_icon: Int = R.mipmap.zd_index_more
    private var show_left_icon: Boolean = false
    private var show_right_icon: Boolean = false
    private var show_right_switch: Boolean = false
    private var show_line: Boolean = false

    private val typeArray = context.obtainStyledAttributes(attrs,R.styleable.setting_view)
    private var view: View? = null
    init {
        view = LayoutInflater.from(context).inflate(R.layout.item_set_layout,this)

        leftTxt = typeArray.getString(R.styleable.setting_view_left_txt)
        rightTxt = typeArray.getString(R.styleable.setting_view_right_txt)
        left_icon = typeArray.getInt(R.styleable.setting_view_left_icon,R.mipmap.ic_launcher)
        right_icon = typeArray.getInt(R.styleable.setting_view_right_icon,R.mipmap.zd_index_more)
        show_left_icon = typeArray.getBoolean(R.styleable.setting_view_show_left_icon,false)
        show_right_icon = typeArray.getBoolean(R.styleable.setting_view_show_right_icon,true)
        show_right_switch = typeArray.getBoolean(R.styleable.setting_view_show_right_switch,false)
        show_line = typeArray.getBoolean(R.styleable.setting_view_show_line,true)
        typeArray.recycle()


        mTvLeftTxt.text = leftTxt
        mTvRightTxt.text = rightTxt
        mIvLeft.setImageResource(left_icon)
        mIvRight.setImageResource(right_icon)
        mIvLeft.visibility = if (show_left_icon) View.VISIBLE else View.GONE
        mIvRight.visibility = if (show_right_icon) View.VISIBLE else View.GONE
        mSwitch.visibility = if (show_right_switch) View.VISIBLE else View.GONE
        mLine.visibility = if (show_line) View.VISIBLE else View.GONE
        mRlLayout.background = ContextCompat.getDrawable(context,R.drawable.selector_click_bg)
    }
}