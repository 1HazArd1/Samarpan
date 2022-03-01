package com.hazard.samarpan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Main2Activity : AppCompatActivity() {
    private var btnLogout: Button? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //this is just for testing the logout functionality remove this while adding the dashboard fragment
        btnLogout=findViewById(R.id.btnLogout)

        btnLogout?.setOnClickListener{
            Firebase.auth.signOut()
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}