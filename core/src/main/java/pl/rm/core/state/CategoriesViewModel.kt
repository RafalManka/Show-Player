package pl.rm.core.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    private val _categories: MutableLiveData<List<Category>> by lazy {
        MutableLiveData<List<Category>>().also { loadUsers() }
    }

    val categories: LiveData<List<Category>>
        get() = _categories

    private fun loadUsers() {
        GlobalScope.launch {
            delay(1000)
            _categories.postValue(mockCategories)
        }
    }
}

private val mockCategories = listOf(
    Category(
        "Movies",
        listOf(
            Movie(
                "Big Buck Bunny",
                "By Blender Foundation",
                "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself. When one sunny day three rodents rudely harass him, something snaps... and the rabbit ain't no bunny anymore! In the typical cartoon tradition he prepares the nasty rodents a comical revenge.\n\nLicensed under the Creative Commons Attribution license\nhttp://www.bigbuckbunny.org",
                listOf("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
                "images/BigBuckBunny.jpg"
            )
        )
    )
)
