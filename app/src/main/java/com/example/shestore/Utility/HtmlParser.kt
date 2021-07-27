package com.example.shestore.Utility

import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat

class HtmlParser {
    companion object {

        /**
         * @param htmlString It takes the HTML String as Input.
         * @return Spanned String which contains the styling as it contains in HTML
         * */
        fun htmlToSpannedString(htmlString: String): Spanned {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(
                    htmlString,
                    Html.FROM_HTML_MODE_COMPACT)
            } else {
                // Used HTML Compat library to support the versions below N
                HtmlCompat.fromHtml("<h2>Title</h2><br><p>Description here</p>", HtmlCompat.FROM_HTML_MODE_LEGACY)
            }
        }
    }
}