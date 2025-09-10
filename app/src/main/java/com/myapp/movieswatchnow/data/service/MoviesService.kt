package com.myapp.movieswatchnow.data.service

import com.myapp.movieswatchnow.domain.MovieDetails
import com.myapp.movieswatchnow.domain.MovieList
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query
import retrofit2.http.Url

interface MoviesService {

    @GET
    suspend fun getMoviesList(
        @Url url: String,
        @HeaderMap headers: Map<String, String> = mapOf(
            "accept" to "application/json",
            "Authorization" to "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1OGFlYjZhODg1MjdkNmNkODhkYTkyYzM0ZGU3OWU5YiIsIm5iZiI6MTc0MzA4NTMxMS43MTI5OTk4LCJzdWIiOiI2N2U1NWVmZmY4Y2JhOTRiYmNiYTM1YzEiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.McCiudTrRZCjc6zrr1pzd7XY5FlXy07ZzO-bOzNBSik"
        ),
        @Query("language") language: String = "en-US"
    ): MovieList

    @GET
    suspend fun getMoviesDetails(
        @Url url: String,
        @HeaderMap headers: Map<String, String> = mapOf(
            "accept" to "application/json",
            "Authorization" to "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1OGFlYjZhODg1MjdkNmNkODhkYTkyYzM0ZGU3OWU5YiIsIm5iZiI6MTc0MzA4NTMxMS43MTI5OTk4LCJzdWIiOiI2N2U1NWVmZmY4Y2JhOTRiYmNiYTM1YzEiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.McCiudTrRZCjc6zrr1pzd7XY5FlXy07ZzO-bOzNBSik"
        ),
        @Query("language") language: String = "en-US"
    ): MovieDetails

    @GET
    suspend fun getMoviesSearch(
        @Url url: String,
        @HeaderMap headers: Map<String, String> = mapOf(
            "accept" to "application/json",
            "Authorization" to "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1OGFlYjZhODg1MjdkNmNkODhkYTkyYzM0ZGU3OWU5YiIsIm5iZiI6MTc0MzA4NTMxMS43MTI5OTk4LCJzdWIiOiI2N2U1NWVmZmY4Y2JhOTRiYmNiYTM1YzEiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.McCiudTrRZCjc6zrr1pzd7XY5FlXy07ZzO-bOzNBSik"
        ),
        @Query("language") language: String = "en-US"
    ): MovieList


}