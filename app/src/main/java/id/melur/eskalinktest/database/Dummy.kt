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
)
