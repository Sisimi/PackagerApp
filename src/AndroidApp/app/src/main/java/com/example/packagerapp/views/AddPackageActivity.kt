package com.example.packagerapp.views

import android.app.Dialog
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.packagerapp.R
import com.example.packagerapp.models.NameValue
import com.example.packagerapp.views.dialogs.ValueDialog
import com.example.packagerapp.views.dialogs.ValueDialogListener
import kotlinx.android.synthetic.main.activity_add_package.*
import kotlinx.android.synthetic.main.toolbar.*

class AddPackageActivity : AppCompatActivity(), ValueDialogListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_package)

        searchEditText.visibility = View.INVISIBLE
        searchIcon.visibility = View.INVISIBLE

        scanQRImageView.setOnClickListener()
        { view ->
            //TODO: Implement QR scan
            //if scan successfull:
            val intent = Intent(this, PackageInfoActivity::class.java)
            startActivity(intent)
        }

        addValueImageView.setOnClickListener(){ view ->
            /*
            val dialog : Dialog = Dialog(this)
            dialog.setContentView(R.layout.layout_customdialog)+
            dialog.setTitle("Add value to the package")

            dialog.show()
            */

            var valueDialog = ValueDialog(view)
            valueDialog.show(supportFragmentManager,"addValueDialog")
        }
    }

    override fun setNameValuePair(nameValue: NameValue) {

    }
}
