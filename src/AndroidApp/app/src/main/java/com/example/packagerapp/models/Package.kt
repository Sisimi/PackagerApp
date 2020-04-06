package com.example.packagerapp.models

data class Package(
                    var id: String?,
                    var packageName: String?,
                    var nameValueList: List<NameValue>?,
                    var description: String?
                   )

