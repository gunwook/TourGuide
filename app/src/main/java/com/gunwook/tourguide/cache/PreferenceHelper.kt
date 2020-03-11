package com.gunwook.tourguide.cache

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.gunwook.tourguide.utils.CodeUtils

class PreferenceHelper {

    fun instance(context : Context) : SharedPreferences {
        return context.getSharedPreferences(CodeUtils.PreferenceCode.PREF_NAME , MODE_PRIVATE)
    }

    fun setPref(context : Context,key : String , value : Any?){
        instance(context).edit{
            when (value) {
                is String? -> it.putString(key, value).apply()
                is Int -> it.putInt(key, value).apply()
                is Boolean -> it.putBoolean(key, value).apply()
                is Float -> it.putFloat(key, value).apply()
                is Long -> it.putLong(key, value).apply()
                else -> throw UnsupportedOperationException("Not yet implemented")
            }
        }
    }

    inline fun <reified T> getPref(context : Context,key : String,defaultValue: T) : T? {
        instance(context).let {
            return get(it,key,defaultValue)
        }
    }

    inline fun <reified T> get(preference : SharedPreferences ,key: String, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> preference.getString(key, defaultValue as? String) as T?
            Int::class -> preference.getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> preference.getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> preference.getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> preference.getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }



    private inline infix fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }
}