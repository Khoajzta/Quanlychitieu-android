package com.example.quanlychitieu.Utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDateToDB(input: String): String {
    return try {
        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val date = inputFormat.parse(input)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        input
    }
}

fun formatMillisToDB(millis: Long?): String {
    if (millis == null) return ""
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(Date(millis))
}