package com.example.packagerapp.views.main

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packagerapp.R
import com.example.packagerapp.di.interactors.LocalDatabaseInteractorModule
import com.example.packagerapp.di.interactors.RemoteDatabaseInteractorModule
import com.example.packagerapp.di.mainpresenter.DaggerMainPresenterComponent
import com.example.packagerapp.models.MyPackage
import com.example.packagerapp.presenters.MainPresenter
import com.example.packagerapp.screens.MainScreen
import com.example.packagerapp.views.addpackage.AddPackageActivity
import com.example.packagerapp.views.main.recyclerview.PackageItem
import com.example.packagerapp.views.main.recyclerview.PackageItemAdapter
import com.example.packagerapp.views.packageinfoActivity.PackageInfoActivity
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainScreen {
    @Inject lateinit var mainPresenter: MainPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter : PackageItemAdapter
    private lateinit var recycleViewLayoutManager: RecyclerView.LayoutManager
    private lateinit var packageItems : MutableList<PackageItem>


//TODO:extract Initial steps to respective methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        applicationContext
        DaggerMainPresenterComponent.builder()
            .localDatabaseInteractorModule(LocalDatabaseInteractorModule(applicationContext))
            .remoteDatabaseInteractorModule(RemoteDatabaseInteractorModule(applicationContext))
            .build()
            .inject(this)

        mainPresenter.attachScreen(this)

        initViews()

        mainPresenter.refreshDB()

        //TODO: To be Implemented
        /*
        var qrScan = IntentIntegrator(this)
        qrScan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
        qrScan.initiateScan()
        */

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //QR code handler logic
        /*
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()
            }
        }
        */
    }

    private fun initViews() {
        searchPackagesSearchView.visibility = View.VISIBLE

        addPackageFB.setOnClickListener {
            val intent = Intent(this, AddPackageActivity::class.java)
            startActivity(intent)
        }

        scanQRImageView.setOnClickListener()
        {
            mainPresenter.startQRScan()
        }

        searchPackagesSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recyclerViewAdapter.filter.filter(newText)
                return false
            }

        })

        initRecycleView()
    }

    private fun initRecycleView() {
        recyclerView = findViewById(R.id.packageItemsRecycleView)
        recycleViewLayoutManager = LinearLayoutManager(this)

        packageItems = initRecycViewItems()
        recyclerViewAdapter = PackageItemAdapter(packageItems,this)

        recyclerView.layoutManager = recycleViewLayoutManager
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun initRecycViewItems() : MutableList<PackageItem> {
        var packageItems = mutableListOf<PackageItem>()

        for(myPackage in mainPresenter.getLocalData())
        {
            packageItems.add(PackageItem(R.drawable.ic_box_solid, R.drawable.ic_box_open_solid, R.drawable.ic_remove_circle_outline_black_24dp,  myPackage))
        }

        return packageItems
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun handlePackageRemoved(position: Int) {
        var item = packageItems.removeAt(position)
        recyclerViewAdapter.notifyItemRemoved(position)

        mainPresenter.deletePackage(item.myPackage.id)
    }

    override fun handleScanResult(myPackage: MyPackage?) {
        if(myPackage == null)
        {
            Toast.makeText(this,"We could not find a package based on this QR code!", Toast.LENGTH_LONG).show()
        }
        else
        {
            //TODO: atadni valami package-et
            val intent = Intent(this, PackageInfoActivity::class.java)
            intent.putExtra("Package Item", myPackage)
            startActivity(intent)
        }
    }


}
