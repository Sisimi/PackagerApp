package com.example.packagerapp.views.addpackage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packagerapp.R
import com.example.packagerapp.di.addpackagepresenter.AddPackagePresenterComponent
import com.example.packagerapp.di.addpackagepresenter.DaggerAddPackagePresenterComponent
import com.example.packagerapp.di.mainpresenter.MainPresenterComponent
import com.example.packagerapp.models.MyPackage
import com.example.packagerapp.models.NameValue
import com.example.packagerapp.presenters.AddPackagePresenter
import com.example.packagerapp.screens.AddPackageScreen
import com.example.packagerapp.views.PackageInfoActivity
import com.example.packagerapp.views.addpackage.recycleview.ValueItem
import com.example.packagerapp.views.addpackage.recycleview.ValueItemAdapter
import com.example.packagerapp.views.addpackage.dialogs.ValueDialog
import com.example.packagerapp.views.addpackage.dialogs.ValueDialogListener
import com.example.packagerapp.views.addpackage.recycleview.NotifyActivity
import com.example.packagerapp.views.main.MainActivity
import kotlinx.android.synthetic.main.activity_add_package.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class AddPackageActivity : AppCompatActivity(), ValueDialogListener, NotifyActivity, AddPackageScreen {
    var addPackagePresenterComponent : AddPackagePresenterComponent = DaggerAddPackagePresenterComponent.create()
    @Inject lateinit var addPackagePresenter : AddPackagePresenter

    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : RecyclerView.Adapter<ValueItemAdapter.ValueItemViewHolder>
    private lateinit var layoutManager: LinearLayoutManager
    private var valueItems = mutableListOf<ValueItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_package)

        addPackagePresenterComponent.inject(this)
        addPackagePresenter.attachScreen(this)

        initViews()
    }

    private fun initViews() {
        searchEditText.visibility = View.INVISIBLE
        searchIcon.visibility = View.INVISIBLE

        scanQRImageView.setOnClickListener()
        { _ ->
            //TODO: Implement QR scan
            //if scan successfull:
            val intent = Intent(this, PackageInfoActivity::class.java)
            startActivity(intent)
        }

        addValueImageView.setOnClickListener(){ view ->
            var valueDialog = ValueDialog(view)
            valueDialog.show(supportFragmentManager,"addValueDialog")
        }

        packageNameEditText.doAfterTextChanged { text->
            addPackagePresenter.setMyPackageName(text.toString())
        }

        packageDescriptionEditText.doAfterTextChanged {text ->
            addPackagePresenter.setMyPackageDescription(text.toString())
        }

        addPackageFB.setOnClickListener(){_ ->
            //TODO: adatbazisba mentes
            addPackagePresenter.addPackageToDB()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        initRecycleView()
    }

    private fun initRecycleView() {
        recyclerView = findViewById(R.id.valuesRecycleView)
        layoutManager = LinearLayoutManager(this)
        adapter = ValueItemAdapter(valueItems, this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun setNameValuePair(nameValue: NameValue) {
        addPackagePresenter.addNewItemToNameValueList(nameValue)

        valueItems.add(ValueItem(R.drawable.ic_tools_solid, R.drawable.ic_remove_circle_outline_black_24dp, nameValue.name + " : " + nameValue.value))
        adapter.notifyItemInserted(valueItems.count() - 1)
    }

    override fun notifyOnItemRemoved(position: Int) {
        valueItems.removeAt(position)
        adapter.notifyItemRemoved(position)

        addPackagePresenter.removeItemFromNameValueList(position)
    }

    override fun handlePackageValidation(isValid: Boolean) {
        if(isValid)
        {
            addPackageFB.visibility = View.VISIBLE
        }
        else
        {
            addPackageFB.visibility = View.GONE;
        }
    }
}