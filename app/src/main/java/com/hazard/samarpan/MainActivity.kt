package com.hazard.samarpan

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(Build.VERSION.SDK_INT < 16){
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        /*This part of the code is used to set the default theme as the theme was set to the splash screen before setting the main activity layout
           remove the comment tag for adding the logic*/
        setTheme(R.style.Theme_Samarpan)
        setContentView(R.layout.activity_main)
    }
}