package id.melur.eskalinktest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Dummy(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "nik") val nik: String,
    @ColumnInfo(name = "nama") val nama: String,
    @ColumnInfo(name = "umur") val umur: Int,
    @ColumnInfo(name = "kota") val kota: String

//    @PrimaryKey
//    @ColumnInfo(name = "id")
//    var id: Int = 0,

//    @PrimaryKey
//    @ColumnInfo(name = "nik")
//    var nik: String = "0",
//
//    @ColumnInfo(name = "nama")
//    var nama: String? = null,
//
//    @ColumnInfo(name = "umur")
//    var umur: Int? = null,
//
//    @ColumnInfo(name = "kota")
//    var kota: String? = null

)
