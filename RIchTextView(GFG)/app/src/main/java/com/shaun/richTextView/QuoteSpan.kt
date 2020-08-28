package com.shaun.richTextView

import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.style.LeadingMarginSpan
import android.text.style.LineBackgroundSpan

class QuoteSpanClass(
    private val backgroundColor: Int,
    private val stripeColor: Int,
    private val stripeWidth: Float,
    private val gap: Float
) :
    LeadingMarginSpan, LineBackgroundSpan {

    //Margin for the block quote tag
    override fun getLeadingMargin(first: Boolean): Int {
        return (stripeWidth + gap).toInt()
    }

    //this function draws the margin.
    override fun drawLeadingMargin(
        c: Canvas,
        p: Paint,
        x: Int,
        dir: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence,
        start: Int,
        end: Int,
        first: Boolean,
        layout: Layout
    ) {

        val style = p.style
        val paintColor = p.color
        p.style = Paint.Style.FILL
        p.color = stripeColor

        //Creating margin according to color and stripewidth it recieves
        //Press CTRL+Q on function name to read more
        c.drawRect(x.toFloat(), top.toFloat(), x + dir * stripeWidth, bottom.toFloat(), p)
        p.style = style
        p.color = paintColor
    }

    override fun drawBackground(
        c: Canvas,
        p: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence,
        start: Int,
        end: Int,
        lnum: Int
    ) {
        val paintColor = p.color
        p.color = backgroundColor

        //It draws the background on which blockquote text is written
        c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), p)
        p.color = paintColor
    }

}