package id.melur.eskalinktest.service

import id.melur.eskalinktest.model.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("b7/datadummy.php")
    fun getData() : Call<DataResponse>

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