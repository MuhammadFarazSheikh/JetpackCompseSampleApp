package com.montymobile.videorbt.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.reflect.Type

class SharedPreferenceUtils(context: Context) {
    val sharedPreferences: SharedPreferences

    init {
        sharedPreferences =
            context.getSharedPreferences("user", Context.MODE_PRIVATE) as SharedPreferences
    }

    /*SAVE ANY VALUE*/
    fun saveValues(dataTypeToSave: String, valueToSave: Any) {
        var editor = sharedPreferences.edit()

        when (valueToSave) {
            is Boolean -> {
                editor.putBoolean(
                    dataTypeToSave, valueToSave
                ).apply()
            }
            is String -> {
                editor.putString(
                    dataTypeToSave, valueToSave
                ).apply()
            }
            is Int -> {
                editor.putInt(
                    dataTypeToSave, valueToSave
                ).apply()
            }
            is Float -> {
                editor.putFloat(
                    dataTypeToSave, valueToSave
                ).apply()
            }
            is Long -> {
                editor.putLong(
                    dataTypeToSave, valueToSave
                ).apply()
            }
        }
    }

    /*READ VALUE*/
    fun readValue(dataTypeToGet: String, defaultValue: Any): Any {
        if (sharedPreferences.contains(dataTypeToGet)
        ) {
            when (defaultValue) {
                is Boolean -> {
                    return sharedPreferences.getBoolean(dataTypeToGet, defaultValue)
                }
                is String -> {
                    return sharedPreferences.getString(dataTypeToGet, defaultValue)!!
                }
                is Int -> {
                    return sharedPreferences.getInt(dataTypeToGet, defaultValue)
                }
                is Float -> {
                    return sharedPreferences.getFloat(dataTypeToGet, defaultValue)
                }
                is Long -> {
                    return sharedPreferences.getLong(dataTypeToGet, defaultValue)
                }
            }
        }
        return defaultValue
    }
}