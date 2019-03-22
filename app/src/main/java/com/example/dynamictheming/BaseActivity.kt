package com.example.dynamictheming

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    private var currentTheme = TEAL

    override fun onCreate(savedInstanceState: Bundle?) {
        currentTheme = PreferenceManager.getDefaultSharedPreferences(this).getInt(KEY_THEME, TEAL)
        super.onCreate(savedInstanceState)
    }

    protected fun setTheme() { setTheme(currentTheme) }
    protected fun switchTheme() {
        currentTheme = when(currentTheme) {
            TEAL -> CYAN
            CYAN -> TEAL
            else -> -1
        }

        PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(KEY_THEME, currentTheme).apply()
    }

    companion object {
        private const val KEY_THEME = "Theme"
        private const val TEAL = R.style.AppTheme_Teal
        private const val CYAN = R.style.AppTheme_Cyan
    }
}