package com.example.farmapplication.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


class MyTextViewMedium : AppCompatTextView {
    constructor(context: Context) : super(context){
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
        val myTypeface: Typeface? =TypefaceProvider.getTypeFace(context, "Montserrat-Medium")
        typeface = myTypeface
    }


    override fun isInEditMode(): Boolean {
        return true
    }
}
