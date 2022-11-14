package id.melur.eskalinktest.service

import id.melur.eskalinktest.model.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val BASE_URL = "http://103.146.244.206:600/"
    }

    @GET("b7/datadummy.php")
    fun getData() : DataResponse

//    @GET("movie/{movie_id}")
//    fun getDetailMovie(
//        @Path("movie_id") movieId: Int,
//        @Query("api_key") apiKey: String
//    ): Call<MoviePopularItem>
//
//    @GET("movie/{movie_id}/reviews")
//    fun getReview(
//        @Path("movie_id") movieId: Int,
//        @Query("api_key") apiKey: String
//    ): Call<MovieReviews>
//
//
//    @GET("movie/{movie_id}/videos")
//    fun getVideos(
//        @Path("movie_id") movieId: Int,
//        @Query("api_key") apiKey: String
//    ): Call<Videos>
}