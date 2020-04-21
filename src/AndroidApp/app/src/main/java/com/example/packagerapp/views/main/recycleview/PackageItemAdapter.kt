package com.example.packagerapp.views.main.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.packagerapp.R

class PackageItemAdapter(packageItems:MutableList<PackageItem>) : RecyclerView.Adapter<PackageItemAdapter.PackageItemViewHolder>() {
    private var packageItems = packageItems


    class PackageItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var packageImageView : ImageView = view.findViewById(R.id.valueIconImageView)
        var packageNameTextView: TextView = view.findViewById(R.id.valueAndNameTextView)
        var rootItem : ConstraintLayout = view.findViewById(R.id.packageItemRoot)
        var subItem: ConstraintLayout = view.findViewById(R.id.packageItemSub)
        var packageDecriptionTextView: TextView = view.findViewById(R.id.packageItemDescriptionTextView)
        var fewPackageValueLinearLayout: LinearLayout = view.findViewById(R.id.fewPackageValueLinearLayout)
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

        holder.packageImageView.setImageResource(currentPackageItem.closedPackageImageResouce)
        holder.packageNameTextView.text = currentPackageItem.packageName
        holder.packageDecriptionTextView.text = currentPackageItem.description

        holder.rootItem.setOnClickListener(){ _ ->
            if (holder.subItem.visibility == View.GONE)
            {
                holder.subItem.visibility = View.VISIBLE
                holder.packageImageView.setImageResource(currentPackageItem.openedPackageImageResource)
            }
            else if(holder.subItem.visibility == View.VISIBLE)
            {
                holder.subItem.visibility = View.GONE
                holder.packageImageView.setImageResource(currentPackageItem.closedPackageImageResouce)
            }
        }
    }


}