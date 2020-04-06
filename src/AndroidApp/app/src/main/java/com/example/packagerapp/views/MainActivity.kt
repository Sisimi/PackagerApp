package com.example.packagerapp.views

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.packagerapp.R
import com.example.packagerapp.di.DaggerMainPresenterComponent
import com.example.packagerapp.di.MainPresenterComponent
import com.example.packagerapp.models.Package
import com.example.packagerapp.presenters.AddPackagePresenter
import com.example.packagerapp.presenters.MainPresenter
import com.example.packagerapp.screens.MainScreen

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainScreen {

    @Inject lateinit var mainPresenter: MainPresenter
    var mainPresenterComponent : MainPresenterComponent = DaggerMainPresenterComponent.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        mainPresenterComponent.inject(this)

        mainPresenter.getPackages()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun openPackageInfoActivity(packageObject: Package) {
        TODO("Not yet implemented")
    }

    override fun refreshList(packages: Package) {
        TODO("Not yet implemented")
    }
}
