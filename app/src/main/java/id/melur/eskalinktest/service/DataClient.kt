package id.melur.eskalinktest.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataClient {

    /**
     * base url untuk hit api
     * dalam constant karena memang ga bakal berubah
     */
//    private const val BASE_URL = BuildConfig.BASE_URL_TMDB


    /**
     * untuk interceptor di level body
     */
    private val logging : HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
        }

    // Crate client untuk retrofit
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    // create instance ApiService pakai lazy supaya sekali bikin dan seterusnya bakal manggil dari memory (yang udah pernah di bikin)
    val instance : ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://103.146.244.206:600/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(ApiService::class.java)
    }
}