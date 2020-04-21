package com.example.packagerapp.views.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.packagerapp.R
import kotlinx.android.synthetic.main.recycleview_package_item.view.*

class PackageItemAdapter(packageItems:MutableList<PackageItem>) : RecyclerView.Adapter<PackageItemAdapter.PackageItemViewHolder>() {
    private var packageItems = packageItems


    class PackageItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var packageImageView : ImageView = view.findViewById(R.id.packageIconImageView)
        var packageNameTextView: TextView = view.findViewById(R.id.packageNameTextView)



    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageItemViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_package_item, parent, false)
        return PackageItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return packageItems.count()
    }

    override fun onBindViewHolder(holder: PackageItemViewHolder, position: Int) {
        var currentPackageItem = packageItems.get(position)

        holder.packageImageView.setImageResource(currentPackageItem.imageResource)
        holder.packageNameTextView.setText(currentPackageItem.packageName)
    }


}