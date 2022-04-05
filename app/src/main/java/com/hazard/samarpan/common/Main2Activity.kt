package com.hazard.samarpan.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hazard.samarpan.R
import com.hazard.samarpan.user.UserDashboardFragment

class Main2Activity : AppCompatActivity() {
//    private var btnLogout: Button? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Samarpan)
        setContentView(R.layout.activity_main2)
        addFragment()

        //this is just for testing the logout functionality remove this while adding the dashboard fragment
/*        btnLogout=findViewById(R.id.btnLogout)

        btnLogout?.setOnClickListener{
            Firebase.auth.signOut()
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
           finish()
     }   */

    }
    private fun addFragment() {
        val dashboard= UserDashboardFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container2,dashboard).commit()
    }
}