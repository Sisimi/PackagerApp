package com.example.packagerapp.views.addpackage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packagerapp.R
import com.example.packagerapp.di.addpackagepresenter.AddPackagePresenterComponent
import com.example.packagerapp.di.addpackagepresenter.DaggerAddPackagePresenterComponent
import com.example.packagerapp.di.interactors.LocalDatabaseInteractorModule
import com.example.packagerapp.models.NameValue
import com.example.packagerapp.presenters.AddPackagePresenter
import com.example.packagerapp.screens.AddPackageScreen
import com.example.packagerapp.services.FirebaseHelper
import com.example.packagerapp.views.packageinfoActivity.PackageInfoActivity
import com.example.packagerapp.views.addpackage.recyclerview.ValueItem
import com.example.packagerapp.views.addpackage.recyclerview.ValueItemAdapter
import com.example.packagerapp.views.addpackage.dialogs.ValueDialog
import com.example.packagerapp.views.addpackage.dialogs.ValueDialogListener
import com.example.packagerapp.views.main.MainActivity
import kotlinx.android.synthetic.main.activity_add_package.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class AddPackageActivity : AppCompatActivity(), ValueDialogListener, AddPackageScreen {
    //var addPackagePresenterComponent : AddPackagePresenterComponent = DaggerAddPackagePresenterComponent.create()
    @Inject lateinit var addPackagePresenter : AddPackagePresenter

    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : RecyclerView.Adapter<ValueItemAdapter.ValueItemViewHolder>
    private lateinit var layoutManager: LinearLayoutManager
    private var valueItems = mutableListOf<ValueItem>()

    private lateinit var firebaseHelper: FirebaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_package)

        //addPackagePresenterComponent.inject(this)
        DaggerAddPackagePresenterComponent.builder()
            .localDatabaseInteractorModule(LocalDatabaseInteractorModule(applicationContext))
            .build()
            .inject(this)

        addPackagePresenter.attachScreen(this)
        firebaseHelper = FirebaseHelper(this)

        initViews()

        firebaseHelper.logStatusEventToFireBase("EnterActivity",this::class.simpleName.toString(),"BackButton")
    }

    private fun initViews() {
        searchPackagesSearchView.visibility = View.INVISIBLE


        scanQRImageView.setOnClickListener()
        {
            firebaseHelper.logStatusEventToFireBase("StartScan",this::class.simpleName.toString(),"ScanButton")

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

        addPackageFB.setOnClickListener(){
            firebaseHelper.logStatusEventToFireBase("AddedPackage",this::class.simpleName.toString(),"AddPackageButton")
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

        packageDescriptionEditText.clearFocus()
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
