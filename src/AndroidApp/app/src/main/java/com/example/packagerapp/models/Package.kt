package com.example.packagerapp.models

data class Package(
                    var id: String?,
                    var packageName: String?,
                    var description: String?,
                    var nameValueList: List<NameValue>?
                   )

