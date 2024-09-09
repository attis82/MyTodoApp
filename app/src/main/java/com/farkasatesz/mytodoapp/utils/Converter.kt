package com.farkasatesz.mytodoapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Converter {
    fun convertDateToString(date: Date): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(date)
    }

    fun convertStringToDate(dateString: String): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.parse(dateString) ?: Date()
    }

}