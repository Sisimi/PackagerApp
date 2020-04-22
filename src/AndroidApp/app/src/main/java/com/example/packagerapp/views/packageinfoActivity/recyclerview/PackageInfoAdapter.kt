package com.example.packagerapp.views.packageinfoActivity.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.packagerapp.R

class PackageInfoAdapter (nameValueItems: List<PackageInfoValueItem>):RecyclerView.Adapter<PackageInfoAdapter.PackageInfoViewHolder>(){

    private var nameValueItems = nameValueItems

    class PackageInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var valueImageView : ImageView = view.findViewById(R.id.valueIconImageView)
        var valueAndNameTextView: TextView = view.findViewById(R.id.packageInfoDescriptionTextView)
        var removeButton : ImageView = view.findViewById(R.id.removeValueImageView)
        init {
            removeButton.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageInfoViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_value_item, parent, false)
        return PackageInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nameValueItems.count()
    }

    override fun onBindViewHolder(holder: PackageInfoViewHolder, position: Int) {
        var currentValueItem = nameValueItems[position]

        holder.valueImageView.setImageResource(currentValueItem.imageResource)
        holder.valueAndNameTextView.text = currentValueItem.nameAndValue
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}