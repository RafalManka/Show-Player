package pl.rm.core.state.tools

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.tvmaze.com/api/")
        .addConverterFactory(GsonConverterFactory.create())

        .build()

    fun <T> create(javaClass: Class<T>): T {
        return retrofit.create(javaClass)
    }
}