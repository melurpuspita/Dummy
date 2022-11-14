package id.melur.eskalinktest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "dummy")
data class Dummy(
    @PrimaryKey
    @ColumnInfo(name = "nik") var nik: String,
    @ColumnInfo(name = "nama") var nama: String,
    @ColumnInfo(name = "umur") var umur: Int,
    @ColumnInfo(name = "kota") var kota: String

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
