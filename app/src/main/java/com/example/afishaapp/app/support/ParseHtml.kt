package com.example.afishaapp.app.support

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object ParseHtml {
    fun getText(html: String?): String {
        if (html == null) {
            return ""
        }

        val document: Document = Jsoup.parse(html)
        return document.text()
    }
}