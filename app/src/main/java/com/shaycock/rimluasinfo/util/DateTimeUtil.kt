package com.shaycock.rimluasinfo.util

import java.lang.Exception
import java.text.SimpleDateFormat

object DateTimeUtil {
    private const val DATE_FORMAT_ISO = "yyyy-MM-dd'T'HH:mm:ss"
    private const val DATE_FORMAT_SIMPLE_TIME_DATE_FULL = "HH:mm:ss dd/MM/yyyy"

    /**
     * Takes an iso datetime string and converts it to a basic readable time format
     * Returns empty string if the input string is malformed
     */
    fun isoToTimeDateFullFormat(dateStr: String?): String {
        if (dateStr == null) return ""
        return try {
            val parser = SimpleDateFormat(DATE_FORMAT_ISO)
            val formatter = SimpleDateFormat(DATE_FORMAT_SIMPLE_TIME_DATE_FULL)
            formatter.format(parser.parse(dateStr)!!)
        } catch (e: Exception) {
            ""
        }
    }
}