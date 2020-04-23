package com.example.packagerapp.views.main.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.packagerapp.R
import com.example.packagerapp.screens.MainScreen

class PackageItemAdapter(packageItems:MutableList<PackageItem>, activity: MainScreen) : RecyclerView.Adapter<PackageItemAdapter.PackageItemViewHolder>(), Filterable {
    private var packageItems = packageItems
    private var packageItemsFull = packageItems.toMutableList()
    private var packageFilter : Filter
    private var activity = activity

    init{
        packageFilter =  object:Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var filteredList = mutableListOf<PackageItem>()

                if(constraint == null || constraint.length == 0)
                {
                    filteredList.addAll(packageItemsFull)
                }
                else
                {
                   var filterPattern = constraint.toString().toLowerCase().trim()
                    for(item in packageItemsFull)
                    {
                        if(item.myPackage.packageName.toLowerCase().contains(filterPattern))
                        {
                            filteredList.add(item)
                        }
                    }
                }

                var filteredResults = FilterResults()
                filteredResults.values = filteredList

                return filteredResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                packageItems.clear()
                packageItems.addAll(results!!.values as MutableList<PackageItem>)
                notifyDataSetChanged()
            }
        }
    }

    class PackageItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var view = view
        var packageImageView : ImageView = view.findViewById(R.id.valueIconImageView)
        var packageNameTextView: TextView = view.findViewById(R.id.packageInfoDescriptionTextView)
        var rootItem : ConstraintLayout = view.findViewById(R.id.packageItemRoot)
        var subItem: ConstraintLayout = view.findViewById(R.id.packageItemSub)
        var packageDecriptionTextView: TextView = view.findViewById(R.id.packageItemDescriptionTextView)
        var fewPackageValueLinearLayout: LinearLayout = view.findViewById(R.id.fewPackageValueLinearLayout)
        var removePackageImageView : ImageView = view.findViewById(R.id.removePackageImageView)
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
        holder.packageNameTextView.text = currentPackageItem.myPackage.packageName
        holder.packageDecriptionTextView.text = currentPackageItem.myPackage.description

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

        //TODO: this functionality should be replaced with "Gmail" like delete method
        holder.removePackageImageView.setOnClickListener(){
            activity.handlePackageRemoved(position)
        }

        for(nameValue in currentPackageItem.myPackage.nameValueList)
        {
            var nameValueTextView = TextView(holder.view.context)
            nameValueTextView.setText(nameValue.name + ":" + nameValue.value)
            holder.fewPackageValueLinearLayout.addView(nameValueTextView)
        }


    }

    override fun getFilter(): Filter {
        return packageFilter
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}