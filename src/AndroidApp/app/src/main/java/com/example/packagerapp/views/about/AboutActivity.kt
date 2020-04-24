package com.example.packagerapp.views.about

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.packagerapp.R
import com.example.packagerapp.services.FirebaseHelper
import com.example.packagerapp.views.main.MainActivity
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    private lateinit var firebaseHelper: FirebaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        firebaseHelper = FirebaseHelper(this)

        proceedToMainButton.setOnClickListener()
        {
            firebaseHelper.logStatusEventToFireBase("ExitActivity",this::class.simpleName.toString(), "ProceedToMainButton")

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        firebaseHelper.logStatusEventToFireBase("EnterApp", this::class.simpleName.toString(), this::class.simpleName.toString())
        firebaseHelper.logStatusEventToFireBase("EnterActivity",this::class.simpleName.toString(), this::class.simpleName.toString())
    }
}
