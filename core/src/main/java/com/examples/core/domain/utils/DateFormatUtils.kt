package com.examples.core.domain.utils

import android.annotation.SuppressLint
import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import com.youxel.core.domain.entities.enums.DateDifference
import com.youxel.core.domain.entities.enums.DateType
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

/**
 * Created by Shehab ELsarky.
 */

fun convertDateStringToMilliSeconds(
    dateFromApi: String,
    inputDateTemplate: String
): Long {
    try {
        val inputSimpleDateFormat = SimpleDateFormat(inputDateTemplate, Locale.US)
        inputSimpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val apiDate = inputSimpleDateFormat.parse(dateFromApi) ?: Date()
        val calendar = Calendar.getInstance()
        calendar.time = apiDate
        calendar.timeZone = TimeZone.getDefault()
        return calendar.time.time
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return 0
}

fun changeDateFormat(
    dateFromApi: String,
    inputDateTemplate: String,
    outputDateTemplate: String
): String {
    return try {
        val inputSimpleDateFormat = SimpleDateFormat(inputDateTemplate, Locale.US)
        inputSimpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val apiDate = inputSimpleDateFormat.parse(dateFromApi) ?: Date()
        val outputSimpleDateFormat = SimpleDateFormat(outputDateTemplate, Locale.getDefault())
        outputSimpleDateFormat.timeZone = TimeZone.getDefault()
        outputSimpleDateFormat.format(apiDate)
    } catch (e: Exception) {
        ""
    }
}


fun changeDateFormatForUpcomingHolidays(
    dateFromApi: String,
    inputDateTemplate: String,
    outputDateTemplate: String
): String {
    return try {
        val inputSimpleDateFormat = SimpleDateFormat(inputDateTemplate, Locale.getDefault())
        inputSimpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val apiDate = inputSimpleDateFormat.parse(dateFromApi) ?: Date()
        val outputSimpleDateFormat = SimpleDateFormat(outputDateTemplate, Locale.US)
        outputSimpleDateFormat.timeZone = TimeZone.getDefault()
        outputSimpleDateFormat.format(apiDate)
    } catch (e: Exception) {
        ""
    }
}

fun changeDateFormatLocale(
    dateFromApi: String,
    inputDateTemplate: String,
    outputDateTemplate: String
): String {
    return try {
        val inputSimpleDateFormat = SimpleDateFormat(inputDateTemplate, Locale.US)
        inputSimpleDateFormat.timeZone = TimeZone.getDefault()
        val apiDate = inputSimpleDateFormat.parse(dateFromApi) ?: Date()
        val outputSimpleDateFormat = SimpleDateFormat(outputDateTemplate, Locale.US)
        outputSimpleDateFormat.timeZone = TimeZone.getDefault()
        outputSimpleDateFormat.format(apiDate)
    } catch (e: Exception) {
        ""
    }
}

fun convertDateToStringDate(date: Date, outputDateFormat: String): String {
    return try {
        val outputSimpleDateFormat = SimpleDateFormat(outputDateFormat, Locale.US)
        outputSimpleDateFormat.timeZone = TimeZone.getDefault()
        outputSimpleDateFormat.format(date)
    } catch (e: Exception) {
        ""
    }
}

fun convertDateToStringDateLocalized(date: Date, outputDateFormat: String): String {
    return try {
        val outputSimpleDateFormat = SimpleDateFormat(outputDateFormat, Locale.getDefault())
        outputSimpleDateFormat.timeZone = TimeZone.getDefault()
        outputSimpleDateFormat.format(date)
    } catch (e: Exception) {
        ""
    }
}

fun convertDateToRelativeDate(
    dateStr: String,
    dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss"
): String {

    val inputSimpleDateFormat = SimpleDateFormat(dateFormat, Locale.US)
    inputSimpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")

    val date = inputSimpleDateFormat.parse(dateStr) ?: Date()

    return DateUtils.getRelativeTimeSpanString(
        date.time,
        Calendar.getInstance().timeInMillis,
        DateUtils.SECOND_IN_MILLIS
    ).toString()
}

@SuppressLint("SimpleDateFormat")
fun convertDateToStringDate(
    calender: Calendar,
    outputDateFormat: String,
    locale: Locale = Locale.getDefault()
): String {
    return try {
        SimpleDateFormat(outputDateFormat, locale).format(calender.timeInMillis)
    } catch (e: Exception) {
        ""
    }
}

fun getDateDifference(
    start_date: String = "",
    end_date: String = "",
    dateDifference: DateDifference,
    dateFormat: String
): String {

    val sdf = SimpleDateFormat(dateFormat, Locale.getDefault())

    try {
        val d1 = sdf.parse(start_date)
        val d2 = sdf.parse(end_date)
        // Calculate time difference in milliseconds
        val differenceInTime = d2.time - d1.time

        return when (dateDifference) {
            DateDifference.SECONDS -> ((differenceInTime / 1000) % 60).toString()
            DateDifference.MINUTES -> ((differenceInTime / (1000 * 60)) % 60).toString()
            DateDifference.HOURS -> ((differenceInTime / (1000 * 60 * 60)) % 24).toString()
            DateDifference.DAYS -> ((differenceInTime / (1000 * 60 * 60 * 24)) % 365).toString()
            DateDifference.YEARS -> (differenceInTime / (1000L * 60 * 60 * 24 * 365)).toString()
        }
    } catch (e: ParseException) {
        e.printStackTrace()
        return ""
    }
}

fun getCurrentDate(): String {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
}

fun getCurrentDateFormatted(): String {
    return SimpleDateFormat("dd/MM/yyyy", Locale.US).format(Date())
}


fun getEndDateOfTheYearFormatted(): String {
    // Create last day of year
    val lastDayOfCurrentYear = Calendar.getInstance()
    lastDayOfCurrentYear[Calendar.DATE] = 31
    lastDayOfCurrentYear[Calendar.MONTH] = 11
    return convertDateToStringDate(lastDayOfCurrentYear, "dd/MM/yyyy", Locale.US)
}

fun getDateByType(date: String, dateType: DateType, regex: String): Int {
    val parsedDate = date.split(regex)
    return when (dateType) {
        DateType.DAY -> parsedDate[0].toInt()
        DateType.MONTH -> parsedDate[1].toInt()
        DateType.YEAR -> parsedDate[2].toInt()
    }
}

fun getAddedDate(date: String, amount: Int): String {
    try {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val c = Calendar.getInstance()
        c.time = sdf.parse(date)
        c.add(Calendar.DATE, amount) // number of days to add
        return sdf.format(c.time)
    }catch (e: Exception) {
       return ""
    }
}

fun getAddedDate(date: String, regex: String,amount: Int): String {
    try {
        val sdf = SimpleDateFormat(regex, Locale.getDefault())
        val c = Calendar.getInstance()
        c.time = sdf.parse(date)
        c.add(Calendar.DATE, amount) // number of days to add
        return sdf.format(c.time)
    }catch (e: Exception) {
        return ""
    }
}

fun getLastDayOfMonthDate(month: Int): String {
    val calendar = Calendar.getInstance()
    calendar[Calendar.MONTH] = month - 1
    calendar[Calendar.DATE] = calendar.getActualMaximum(Calendar.DATE)
    val date = calendar.time
    val dateFormat = SimpleDateFormat("dd/MM", Locale.US)
    return dateFormat.format(date)
}

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentYear(): Int {
    return Year.now().value
}

fun convertDateStringToMilliSeconds(date: String): Long {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val convertedDate = sdf.parse(date)
    return convertedDate.time
}

fun convertDateStringToMillSeconds(date: String,regex: String): Long {
    val sdf = SimpleDateFormat(regex, Locale.getDefault())
    val convertedDate = sdf.parse(date)
    return convertedDate.time
}

fun changeDateToFormat(outPutFormat: String, date: Date): String {
    val outputSimpleDateFormat = SimpleDateFormat(outPutFormat, Locale.US)
    return outputSimpleDateFormat.format(date)
}


fun changeDateToEnglishFormat(outPutFormat: String, date: Date): String {
    val outputSimpleDateFormat = SimpleDateFormat(outPutFormat, Locale.ENGLISH)
    return outputSimpleDateFormat.format(date)
}


fun getStartDayOfMonth(pattern: String): String {
    val calendar = Calendar.getInstance(Locale.US)
    calendar.add(Calendar.MONTH, 0)
    calendar[Calendar.DATE] = calendar.getActualMinimum(Calendar.DAY_OF_MONTH)
    val monthFirstDay = calendar.time
    calendar[Calendar.DATE] = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val df = SimpleDateFormat(pattern, Locale.US)
    val startDateStr = df.format(monthFirstDay)
    return startDateStr
}

fun getEndDayOfMonth(pattern: String): String {
    val calendar = Calendar.getInstance(Locale.US)
    calendar.add(Calendar.MONTH, 0)
    calendar[Calendar.DATE] = calendar.getActualMinimum(Calendar.DAY_OF_MONTH)
    val monthFirstDay = calendar.time
    calendar[Calendar.DATE] = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val monthLastDay = calendar.time

    val df = SimpleDateFormat(pattern, Locale.US)
    val endDateStr = df.format(monthLastDay)
    return endDateStr
}

fun getCurrentDateFormatted(isDayEnded: Boolean): String {
    if (isDayEnded) {
        val calender = Calendar.getInstance(Locale.US)
        calender.time = Date()
        calender.add(Calendar.DAY_OF_WEEK, 1)
        return SimpleDateFormat("dd/MM/yyyy", Locale.US).format(calender.time)

    } else {
        return SimpleDateFormat("dd/MM/yyyy", Locale.US).format(Date())
    }
}

fun formatDayOrMonthString(dayOrMonth: Int): String {
    var formattedString = dayOrMonth.toString()
    if (formattedString.length == 1)
        formattedString = "0$formattedString"
    return formattedString
}

fun convertDateToTimeStamp(
    dateFromApi: String,
    inputDateTemplate: String
): Long {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(
        inputDateTemplate, Locale.ROOT
    )
    return OffsetDateTime.parse(dateFromApi, formatter)
        .toInstant()
        .toEpochMilli()
}

fun formatTimeStampToDate(timestamp: Long, outputFormat: String): String {
    val timeD = Date(timestamp * 1000)
    val sdf = SimpleDateFormat(outputFormat, Locale.US)
    return sdf.format(timeD)
}
