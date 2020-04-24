package com.example.packagerapp.views.packageinfoActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packagerapp.R
import com.example.packagerapp.di.interactors.LocalDatabaseInteractorModule
import com.example.packagerapp.di.packageinfopresenter.DaggerPackageInfoPresenterComponent
import com.example.packagerapp.models.MyPackage
import com.example.packagerapp.models.NameValue
import com.example.packagerapp.presenters.PackageInfoPresenter
import com.example.packagerapp.screens.PackageInfoScreen
import com.example.packagerapp.services.FirebaseHelper

import com.example.packagerapp.views.main.MainActivity
import com.example.packagerapp.views.packageinfoActivity.recyclerview.PackageInfoAdapter
import com.example.packagerapp.views.packageinfoActivity.recyclerview.PackageInfoValueItem
import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.analytics.Tracker
import kotlinx.android.synthetic.main.activity_package_info.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class PackageInfoActivity : AppCompatActivity(), PackageInfoScreen {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PackageInfoAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    @Inject lateinit var packageInfoPresenter: PackageInfoPresenter

    private lateinit var firebaseHelper : FirebaseHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_info)

        DaggerPackageInfoPresenterComponent.builder()
            .localDatabaseInteractorModule(LocalDatabaseInteractorModule(applicationContext))
            .build()
            .inject(this)

        packageInfoPresenter.attachScreen(this)
        firebaseHelper = FirebaseHelper(this)

        //var application = application as AnalyticsApplication
        //mTracker = application.getDefaultTracker()

        packageInfoPresenter.myPackage = intent.getParcelableExtra("Package Item")!!

        searchPackagesSearchView.visibility = View.INVISIBLE

        scanQRImageView.setOnClickListener() {
            packageInfoPresenter.scanQRCode()
        }

        packageInfoNameTextView.text = packageInfoPresenter.myPackage.packageName
        packageInfoDescriptionTextView.text = packageInfoPresenter.myPackage.description

        initRecyclerView()

        firebaseHelper.logStatusEventToFireBase("EnterActivity",this::class.simpleName.toString(), this::class.simpleName.toString())
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.packageValueRecycleView)
        layoutManager = LinearLayoutManager(this)

        adapter = PackageInfoAdapter(convertToValueItem(packageInfoPresenter.myPackage.nameValueList))

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun convertToValueItem(nameValueList: MutableList<NameValue>): List<PackageInfoValueItem>
    {
        var packageInfoValueItem = mutableListOf<PackageInfoValueItem>()
        for(nameValue in nameValueList)
        {
            packageInfoValueItem.add(PackageInfoValueItem(R.drawable.ic_tools_solid, nameValue.name + " : " + nameValue.value))
        }

        return packageInfoValueItem
    }

    override fun onBackPressed() {
        firebaseHelper.logStatusEventToFireBase("ExitActivity",this::class.simpleName.toString(),"BackButton")

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    override fun handleScanResult(myPackage: MyPackage?) {
        firebaseHelper.logStatusEventToFireBase("ScanSuccessful",this::class.simpleName.toString(),"ScanActivity")

        //TODO: Code duplication, extract this snippet somewhere
        //TODO: Implement QR scan
        //if scan successfull:
        val intent = Intent(this, PackageInfoActivity::class.java)
        intent.putExtra("Package Item", myPackage)
        startActivity(intent)
    }
}
