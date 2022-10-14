package com.tools.tvproject.net

import android.content.Context
import com.tools.tvproject.BaseApplication

/**
 * Created by iamwxy on 2020/7/6 16:07
 * description:
 */
object Pref {
    private val sp by lazy {
        BaseApplication.context.getSharedPreferences(
            BaseApplication.context.packageName,
            Context.MODE_PRIVATE
        )
    }
    private val et by lazy {
        sp.edit()
    }

    /*
        Boolean数据
     */
    fun putBoolean(key: String, value: Boolean) {
        et.putBoolean(key, value)
        et.commit()
    }

    /*
        默认 false
     */
    fun getBoolean(key: String): Boolean {
        return sp.getBoolean(key, false)
    }

    /*
        String数据
     */
    fun putString(key: String, value: String?) {
        et.putString(key, value)
        et.commit()
    }

    /*
        默认 ""
     */
    fun getString(key: String): String? {
        return sp.getString(key, null)
    }

    /*
        Int数据
     */
    fun putInt(key: String, value: Int) {
        et.putInt(key, value)
        et.commit()
    }

    /*
        默认 0
     */
    fun getInt(key: String): Int {
        return sp.getInt(key, 0)
    }

    /*
        Long数据
     */
    fun putLong(key: String, value: Long) {
        et.putLong(key, value)
        et.commit()
    }

    /*
        默认 0
     */
    fun getLong(key: String): Long {
        return sp.getLong(key, 0)
    }

    /*
        删除key数据
     */
    fun remove(key: String) {
        et.remove(key)
        et.commit()
    }
}