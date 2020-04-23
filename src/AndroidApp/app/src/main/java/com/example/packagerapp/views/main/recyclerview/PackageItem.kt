package com.example.packagerapp.views.main.recyclerview

import com.example.packagerapp.models.MyPackage

class PackageItem(closedPackageImageResouce: Int, openedPackageImageResource: Int, removePackageImageResource: Int,
                  myPackage : MyPackage
) {
    var closedPackageImageResouce: Int = closedPackageImageResouce
    var openedPackageImageResource: Int = openedPackageImageResource
    var removePackageImageResource: Int = removePackageImageResource
    var myPackage = myPackage
}