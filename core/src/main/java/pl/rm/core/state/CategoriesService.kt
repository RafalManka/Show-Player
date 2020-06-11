package pl.rm.core.state

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CategoriesService {

    @GET
    suspend fun getCategories(@Url url: String): Response<List<Category>>
}