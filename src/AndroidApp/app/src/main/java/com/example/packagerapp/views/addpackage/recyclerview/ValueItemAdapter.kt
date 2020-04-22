package com.example.packagerapp.views.addpackage.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.packagerapp.R
import com.example.packagerapp.screens.AddPackageScreen

class ValueItemAdapter (nameValueList: MutableList<ValueItem>, activity: AddPackageScreen) :RecyclerView.Adapter<ValueItemAdapter.ValueItemViewHolder>() {

    private var nameValueList = nameValueList
    private var activity = activity

    class ValueItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var valueImageView : ImageView = view.findViewById(R.id.valueIconImageView)
        var valueAndNameTextView: TextView = view.findViewById(R.id.packageInfoDescriptionTextView)
        var removeImageView : ImageView = view.findViewById(R.id.removeValueImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValueItemViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_value_item, parent, false)
        return ValueItemAdapter.ValueItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nameValueList.count()
    }

    override fun onBindViewHolder(holder: ValueItemViewHolder, position: Int) {
        var currentValueItem = nameValueList[position]

        holder.valueImageView.setImageResource(currentValueItem.imageResource)
        holder.valueAndNameTextView.text = currentValueItem.nameAndValue
        holder.removeImageView.setOnClickListener()
        {
            activity.notifyOnItemRemoved(position)
        }
    }

    /*
    Below is Remove item hotfix
     */

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}