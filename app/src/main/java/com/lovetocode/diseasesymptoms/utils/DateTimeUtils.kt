package com.montymobile.callsignature.utils

import android.text.format.DateFormat
import com.montymobile.Constants
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DateTimeUtils
{
    companion object
    {
        /*GET CURRENT DATE BY DATE FORMAT*/
        public fun getCurrentDateByFormat(dateFormat:String):String
        {
            try {
                return SimpleDateFormat(dateFormat).format(Calendar.getInstance().time).toString()
            }
            catch (e:Exception)
            {
                e.toString()
            }
            return ""
        }
    }
}