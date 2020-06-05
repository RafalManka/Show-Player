package pl.rm.core.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.rm.core.state.tools.RetrofitProvider

class CategoriesViewModel(
    private val service: CategoriesService = RetrofitProvider.create(
        CategoriesService::class.java
    )
) : ViewModel() {

    private val _categories: MutableLiveData<List<Category>> by lazy {
        MutableLiveData<List<Category>>().also { loadCategories() }
    }

    val categories: LiveData<List<Category>>
        get() = _categories

    private fun loadCategories() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                val categories =
                    service.getCategories("https://api.jsonbin.io/b/5ed4fd8c7741ef56a565edf6")
                if (categories.isSuccessful) {
                    _categories.postValue(categories.body())
                }
            }
        }
    }

}
