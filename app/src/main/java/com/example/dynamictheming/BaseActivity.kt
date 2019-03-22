package com.example.dynamictheming

import android.app.ActivityManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    private var currentTheme = TEAL

    override fun onCreate(savedInstanceState: Bundle?) {
        currentTheme = PreferenceManager.getDefaultSharedPreferences(this).getInt(KEY_THEME, TEAL)
        super.onCreate(savedInstanceState)
    }

    protected fun setTheme() {
        setTheme(currentTheme)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTaskDescription(
                ActivityManager.TaskDescription(
                getString(R.string.app_name),
                BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher),
                ContextCompat.getColor(this, getColorPrimary())
            ))
        }
    }
    protected fun switchTheme() {
        currentTheme = when(currentTheme) {
            TEAL -> CYAN
            CYAN -> TEAL
            else -> -1
        }

        PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(KEY_THEME, currentTheme).apply()
    }

    private fun getColorPrimary() = when(currentTheme) {
        TEAL -> R.color.colorPrimaryTeal
        CYAN -> R.color.colorPrimaryCyan
        else -> android.R.color.background_light
    }

    companion object {
        private const val KEY_THEME = "Theme"
        private const val TEAL = R.style.AppTheme_Teal
        private const val CYAN = R.style.AppTheme_Cyan
    }
}