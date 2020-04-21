package com.example.packagerapp.views.main

import android.content.Intent
import android.os.Bundle
import android.view.View
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
import com.example.packagerapp.views.PackageInfoActivity
import com.example.packagerapp.views.main.recycleview.PackageItem
import com.example.packagerapp.views.main.recycleview.PackageItemAdapter
import kotlinx.android.synthetic.main.activity_main.addPackageFB
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainScreen {
    @Inject lateinit var mainPresenter: MainPresenter
    var mainPresenterComponent : MainPresenterComponent = DaggerMainPresenterComponent.create()

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter : RecyclerView.Adapter<PackageItemAdapter.PackageItemViewHolder>
    private lateinit var recycleViewLayoutManager: RecyclerView.LayoutManager
    private var packageItems = mutableListOf<PackageItem>()


//TODO:extract Initial steps to respective methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appContext = applicationContext
        mainPresenterComponent.inject(this)
        mainPresenter.attachScreen(this)

        initViews()

        mainPresenter.addPackage()
    }

    private fun initViews() {
        searchEditText.visibility = View.VISIBLE
        searchIcon.visibility = View.VISIBLE

        addPackageFB.setOnClickListener { view ->
            val intent = Intent(this, AddPackageActivity::class.java)
            startActivity(intent)
        }

        scanQRImageView.setOnClickListener()
        { view ->
            //TODO: Implement QR scan
            //if scan successfull:
            val intent = Intent(this, PackageInfoActivity::class.java)
            startActivity(intent)
        }

        initRecycleView()
    }

    private fun initRecycleView() {
        recyclerView = findViewById(R.id.packageItemsRecycleView)
        recycleViewLayoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = PackageItemAdapter(packageItems)

        recyclerView.layoutManager = recycleViewLayoutManager
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun openPackageInfoActivity(packageObject: MyPackage) {
        TODO("Not yet implemented")
    }

    override fun refreshList(packages: List<MyPackage?>?) {
        var asd : Package? = null
        TODO("Not yet implemented")
    }


    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}
