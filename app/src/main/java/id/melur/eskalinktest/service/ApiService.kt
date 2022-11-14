package id.melur.eskalinktest.service

import id.melur.eskalinktest.model.DataResponse
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val BASE_URL = "http://103.146.244.206:600/"
    }

    @GET("b7/datadummy.php")
    suspend fun getData() : DataResponse
}