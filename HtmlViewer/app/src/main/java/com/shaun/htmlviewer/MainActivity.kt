package com.shaun.htmlviewer

import android.os.Bundle
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.text.style.ImageSpan
import android.text.style.QuoteSpan
import android.text.style.URLSpan
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import kotlinx.android.synthetic.main.activity_main.*
private const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display_html.setOnClickListener {
          displayHtml()
        }
    }

    private fun displayHtml() {
        val imageGetter = ImageGetter(resources, html_viewer)
        val styledText = HtmlCompat.fromHtml(
            htmlString().html,
            HtmlCompat.FROM_HTML_MODE_LEGACY,
            imageGetter,
            null
        )
          ImageClick(styledText as Spannable)
         replaceQuoteSpans(styledText as Spannable)



        html_viewer.text = styledText
        html_viewer.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun replaceQuoteSpans(spannable: Spannable) {
        val quoteSpans: Array<QuoteSpan> =
            spannable.getSpans(0, spannable.length - 1, QuoteSpan::class.java)

        for (quoteSpan in quoteSpans) {
            val start: Int = spannable.getSpanStart(quoteSpan)
            val end: Int = spannable.getSpanEnd(quoteSpan)
            val flags: Int = spannable.getSpanFlags(quoteSpan)
            spannable.removeSpan(quoteSpan)
            spannable.setSpan(
                QuoteSpanClass(
                    ContextCompat.getColor(this, R.color.Grey),
                    ContextCompat.getColor(this, R.color.colorAccent),
                    10F,
                    50F
                ),
                start,
                end,
                flags
            )
        }

    }

}
fun ImageClick(html: Spannable) {
    for (span in html.getSpans(0, html.length, ImageSpan::class.java)) {
        val flags = html.getSpanFlags(span)
        val start = html.getSpanStart(span)
        val end = html.getSpanEnd(span)
        html.setSpan(object : URLSpan(span.source) {
            override fun onClick(v: View) {
                Log.d(TAG, "onClick: url is ${span.source}")
            }
        }, start, end, flags)
    }
}
