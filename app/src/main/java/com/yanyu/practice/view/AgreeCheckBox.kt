package com.yanyu.practice.view

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.SpannedString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.yanyu.practice.R
import kotlinx.android.synthetic.main.agree_check_box.view.*

/**
 * Created by 二哈 on 2020/11/9.
 */
class AgreeCheckBox @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var isChecked = false
    private val isSelectRes = R.mipmap.ic_dd_gx
    private val isUnSelectRes = R.mipmap.ic_dd_wgx
    private var mClickSpanCallback: ClickSpanCallback? = null

    init {
        inflate(getContext(),R.layout.agree_check_box,this)
        initSpan()
        mIvCheck.setOnClickListener {
            changeUiState()
        }
    }
    private fun initSpan() {
        val span = SpannableString(resources.getString(R.string.agreement))
        span.setSpan(object : ClickableSpan(){
            override fun onClick(widget: View) {
                mClickSpanCallback?.click(1)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(context,R.color.app_color_green)
                ds.isUnderlineText = false
            }

        },7,13,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan(){
            override fun onClick(widget: View) {
                mClickSpanCallback?.click(2)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(context,R.color.app_color_green)
                ds.isUnderlineText = false
            }

        },14,20,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        mTvAgreement.movementMethod = LinkMovementMethod.getInstance()
        mTvAgreement.highlightColor = Color.TRANSPARENT
        mTvAgreement.text = span
    }
    fun changeUiState() {
        isChecked = !isChecked
        if (isChecked) {
            mIvCheck.setImageResource(isSelectRes)
        }else {
            mIvCheck.setImageResource(isUnSelectRes)
        }
    }
    fun isChecked(): Boolean = isChecked
    fun setChecked(isChecked: Boolean) {
        this.isChecked = isChecked
        if (isChecked) {
            mIvCheck.setImageResource(isSelectRes)
        }else {
            mIvCheck.setImageResource(isUnSelectRes)
        }
    }

    interface ClickSpanCallback{
        fun click(type: Int)
    }
    fun setClickSpanCallback(mClickSpanCallback: ClickSpanCallback){
        this.mClickSpanCallback = mClickSpanCallback
    }
}