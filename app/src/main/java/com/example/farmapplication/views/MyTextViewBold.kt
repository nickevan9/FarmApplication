package com.example.farmapplication.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class MyTextViewBold : AppCompatTextView {
    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initView()
    }

    private fun initView() {
        val myTypeface = TypefaceProvider.getTypeFace(context, "Montserrat-Bold")
        typeface = myTypeface
    }

    override fun isInEditMode(): Boolean {
        return true
    }
}
