package com.example.packagerapp.views.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.packagerapp.R
import com.example.packagerapp.models.NameValue

class ValueDialog(view: View) : AppCompatDialogFragment() {
    private lateinit var editTextName : EditText
    private lateinit var editTextValue : EditText
    private lateinit var listener : ValueDialogListener


    //TODO: missing validation
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var view = activity!!.layoutInflater.inflate(R.layout.layout_customdialog, null)

        editTextName = view.findViewById(R.id.valueNameEditText)
        editTextValue = view.findViewById(R.id.valueEditText)

        return AlertDialog.Builder(activity)
            .setView(view)
            .setTitle("Add value to Package")
            .setNegativeButton("Cancel", DialogInterface.OnClickListener(){dialog, which ->

            })
            .setPositiveButton("Ok", DialogInterface.OnClickListener(){dialog, which ->
                var valueName = editTextName.text.toString()
                var value = editTextValue.text.toString()

                listener.setNameValuePair(NameValue(valueName, value))
            })
            .create()
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        listener = context as ValueDialogListener;
    }
}