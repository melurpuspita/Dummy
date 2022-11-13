package id.melur.eskalinktest.model


import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("data")
    val `data`: List<DataItem>
)