package com.example.packagerapp.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packagerapp.R
import com.example.packagerapp.di.DaggerMainPresenterComponent
import com.example.packagerapp.di.MainPresenterComponent
import com.example.packagerapp.misc.appContext
import com.example.packagerapp.models.MyPackage
import com.example.packagerapp.presenters.MainPresenter
import com.example.packagerapp.screens.MainScreen
import com.example.packagerapp.views.recycleview.PackageItem
import com.example.packagerapp.views.recycleview.PackageItemAdapter
import kotlinx.android.synthetic.main.activity_add_package.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.addPackageFB
import kotlinx.android.synthetic.main.activity_main.mainToolbar
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainScreen {

    @Inject lateinit var mainPresenter: MainPresenter
    var mainPresenterComponent : MainPresenterComponent = DaggerMainPresenterComponent.create()
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter : RecyclerView.Adapter<PackageItemAdapter.PackageItemViewHolder>
    private lateinit var recycleViewLayoutManager: RecyclerView.LayoutManager


//TODO:extract Initial steps to respective methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContext = applicationContext
        mainPresenterComponent.inject(this)

        setContentView(R.layout.activity_main)

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

        //RecyclerViewInit
        recyclerView = findViewById(R.id.packageItemsRecycleView)
        recycleViewLayoutManager = LinearLayoutManager(this)

        //RecycleViewTest
        val items = mutableListOf<PackageItem>()
        for (i in (0..99))
        {
            items.add(PackageItem(R.drawable.ic_box_solid, "PackageTest" + i))
        }

        recyclerViewAdapter = PackageItemAdapter(items)
        recyclerView.layoutManager = recycleViewLayoutManager
        recyclerView.adapter = recyclerViewAdapter


        mainPresenter.attachScreen(this)

        //mainPresenter.getPackages()
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
