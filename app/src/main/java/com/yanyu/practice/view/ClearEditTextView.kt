package com.yanyu.practice.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.yanyu.practice.R

/**
 * Created by 二哈 on 2020/11/9.
 */
class ClearEditTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {
    private var draw: Drawable? = null
    private var drawShow: Drawable? = null
    init {
        draw = ContextCompat.getDrawable(getContext(),R.mipmap.icon_image_delete)
        draw!!.setBounds(0,0,draw!!.minimumWidth,draw!!.minimumHeight)
        isShow(false)
        addTextChangedListener(TextWatcherEditText())
    }

    private fun isShow(isShow: Boolean) {
        drawShow = if (isShow) {
            draw
        } else {
            null
        }
        setCompoundDrawables(compoundDrawables[0],compoundDrawables[1],drawShow,compoundDrawables[3])
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event!!.action == MotionEvent.ACTION_DOWN) {
            var isDelete = event.x > (width - totalPaddingRight) && event.x < (width - paddingRight)
                    && event.y > 0 && event.y < (height - paddingBottom)
            if (isDelete) {
                setText("")
            }
         }
        return super.onTouchEvent(event)
    }

    private inner class TextWatcherEditText: TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            var textContent = s.toString()
            val length = textContent.length
            if (length == 4 || length == 9) {
                if (textContent.substring(length-1) == " ") {
                    textContent = textContent.substring(0,length-1)
                    setText(textContent)
                    setSelection(textContent.length)
                }else {
                    textContent = textContent.substring(0,length-1)+" "+textContent.substring(length-1)
                    setText(textContent)
                    setSelection(textContent.length)
                }

            }
        }

        override fun afterTextChanged(s: Editable?) {
            if (TextUtils.isEmpty(text.toString())) {
                isShow(false)
            }else {
                isShow(true)
            }
        }

    }
}