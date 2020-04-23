package com.example.packagerapp.models

import androidx.room.TypeConverter
import com.example.packagerapp.models.NameValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class NameValueConverter {
    @TypeConverter
    fun fromNameValueList(nameValueList: List<NameValue?>?): String? {
        if (nameValueList == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<NameValue?>?>() {}.type
        return gson.toJson(nameValueList, type)
    }

    @TypeConverter
    fun toNameValueList(nameValueString: String?): List<NameValue>? {
        if (nameValueString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<NameValue?>?>() {}.type
        return gson.fromJson<List<NameValue>>(nameValueString, type)
    }
}