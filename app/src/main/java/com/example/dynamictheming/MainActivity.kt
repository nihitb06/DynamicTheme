package com.example.dynamictheming

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme()
        setContentView(R.layout.activity_main)

        changeThemeButton.setOnClickListener {
            switchTheme()
            recreate()
        }
    }
}
