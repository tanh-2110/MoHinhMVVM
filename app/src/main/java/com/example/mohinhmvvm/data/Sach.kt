package com.example.mohinhmvvm.data

data class Sach(
    var id: String = "",      // id Firebase document
    var icon: Int = 0,
    var name: String = "",
    var mota: String = "",
    var gia: String = "",
    var status: Boolean = false
)
