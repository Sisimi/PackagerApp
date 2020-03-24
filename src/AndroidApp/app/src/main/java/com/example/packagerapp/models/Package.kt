package com.example.packagerapp.models

data class Package(var name: String?,
                   val values: List<NameValue>?,
                   var description: String?
                   )

data class NameValue(var name: String,
                     var value: String
)

Error