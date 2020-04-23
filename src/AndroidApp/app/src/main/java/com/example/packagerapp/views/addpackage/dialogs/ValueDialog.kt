package com.example.packagerapp.views.addpackage.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.packagerapp.R
import com.example.packagerapp.models.NameValue

class ValueDialog(view: View) : AppCompatDialogFragment() {
    private lateinit var editTextName : EditText
    private lateinit var editTextValue : EditText
    private lateinit var listener : ValueDialogListener


    //TODO: missing validation
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var view = requireActivity().layoutInflater.inflate(R.layout.layout_customdialog, null)

        editTextName = view.findViewById(R.id.valueNameEditText)
        editTextValue = view.findViewById(R.id.valueEditText)

        return AlertDialog.Builder(activity)
            .setView(view)
            .setTitle("Add value to Package")
            .setNegativeButton("Cancel", DialogInterface.OnClickListener(){ _, _ ->

            })
            .setPositiveButton("Ok", DialogInterface.OnClickListener(){ _, _ ->
                var valueName = editTextName.text.toString()
                var value = editTextValue.text.toString()

                if(valueName == "" || valueName == null ||
                        value == "" || value == null)
                {
                    Toast.makeText(activity, "Not all input has been filled!",Toast.LENGTH_SHORT)
                }
                else
                {
                    listener.setNameValuePair(NameValue(valueName, value))
                }
            })
            .create()
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        listener = context as ValueDialogListener;
    }
}