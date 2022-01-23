package com.lovetocode.diseasesymptoms.utils

import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtils {
    companion object {

        /*GET CURRENT DATE BY DATE FORMAT*/
        public fun getCurrentDateByFormat(
            dateFormat: String): String {
            return SimpleDateFormat(dateFormat).format(Calendar.getInstance().time).toString()
        }

        /*CONVERT DATE BY FORMAT*/
        public fun convertDateByFormat(
            oldDateFormat: String
            ,newDateFormat:String
            ,dateValue:String):String{
            return SimpleDateFormat(newDateFormat).format(SimpleDateFormat(oldDateFormat).parse(dateValue))
        }
    }
}