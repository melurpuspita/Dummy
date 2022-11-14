package id.melur.eskalinktest.model


import com.google.gson.annotations.SerializedName
import id.melur.eskalinktest.database.Dummy

data class DataItem(
    @SerializedName("kota")
    val kota: String? = null,
    @SerializedName("nama")
    val nama: String? = null,
    @SerializedName("nik")
    val nik: String,
    @SerializedName("umur")
    val umur: Int? = 0
) {

    fun toDataEntity(): Dummy =
        Dummy(
            nik = nik,
            nama = nama,
            umur = umur,
            kota = kota
        )
}