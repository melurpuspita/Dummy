package id.melur.eskalinktest.model


import com.google.gson.annotations.SerializedName
import id.melur.eskalinktest.database.Dummy

data class DataItem(
    @SerializedName("kota")
    val kota: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("nik")
    val nik: String = "0",
    @SerializedName("umur")
    val umur: Int
) {

    fun toDataEntity(): Dummy =
        Dummy(
            id = 0,
            nik = nik,
            nama = nama,
            umur = umur,
            kota = kota
        )
}