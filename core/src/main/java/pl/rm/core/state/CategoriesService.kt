package pl.rm.core.state

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface Categoriesservice {

    @GET
    fun getCategories(@Url url: String): Call<List<Category>>
}