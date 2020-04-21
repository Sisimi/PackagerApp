package com.example.packagerapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.packagerapp.R
import com.example.packagerapp.views.main.MainActivity
import kotlinx.android.synthetic.main.toolbar.*

class PackageInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_info)

        searchEditText.visibility = View.INVISIBLE
        searchIcon.visibility = View.INVISIBLE

        scanQRImageView.setOnClickListener() { view ->
            //TODO: Code duplication, extract this snippet somewhere
            //TODO: Implement QR scan
            //if scan successfull:
            val intent = Intent(this, PackageInfoActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}
