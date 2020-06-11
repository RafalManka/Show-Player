package pl.rm.core.state

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import pl.rm.core.state.tools.RetrofitProvider

class CategoriesViewModel(
    private val service: CategoriesService = RetrofitProvider.create(
        CategoriesService::class.java
    )
) : ViewModel() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private val _categories: MutableLiveData<List<Category>> by lazy {
        MutableLiveData<List<Category>>().also { loadCategories() }
    }

    val categories: LiveData<List<Category>>
        get() = _categories

    private fun loadCategories() {
        scope.launch {
            val categories =
                service.getCategories("https://api.jsonbin.io/b/5ed4fd8c7741ef56a565edf6")
            if (categories.isSuccessful) {
                _categories.postValue(categories.body())
            }
            job.complete()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
