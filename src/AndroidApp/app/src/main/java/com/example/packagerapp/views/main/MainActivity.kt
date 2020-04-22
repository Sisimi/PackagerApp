package com.example.packagerapp.views.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packagerapp.R
import com.example.packagerapp.di.mainpresenter.DaggerMainPresenterComponent
import com.example.packagerapp.di.mainpresenter.MainPresenterComponent
import com.example.packagerapp.misc.appContext
import com.example.packagerapp.models.MyPackage
import com.example.packagerapp.presenters.MainPresenter
import com.example.packagerapp.screens.MainScreen
import com.example.packagerapp.views.addpackage.AddPackageActivity
import com.example.packagerapp.views.packageinfoActivity.PackageInfoActivity
import com.example.packagerapp.views.main.recyclerview.PackageItem
import com.example.packagerapp.views.main.recyclerview.PackageItemAdapter
import kotlinx.android.synthetic.main.activity_main.addPackageFB
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainScreen {
    @Inject lateinit var mainPresenter: MainPresenter
    //var mainPresenterComponent : MainPresenterComponent = DaggerMainPresenterComponent.create()

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter : PackageItemAdapter
    private lateinit var recycleViewLayoutManager: RecyclerView.LayoutManager
    private lateinit var packageItems : MutableList<PackageItem>


//TODO:extract Initial steps to respective methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appContext = applicationContext
        //mainPresenterComponent.inject(this)
        DaggerMainPresenterComponent.create().inject(this)
        mainPresenter.attachScreen(this)

        initViews()

        mainPresenter.refreshDB()
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
