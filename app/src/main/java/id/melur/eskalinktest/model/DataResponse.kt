package id.melur.eskalinktest.model


import com.google.gson.annotations.SerializedName

data class DataResponse(
    @field:SerializedName("data")
    val `data`: List<DataItem>
)