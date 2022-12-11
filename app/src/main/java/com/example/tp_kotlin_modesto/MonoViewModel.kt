import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tp_kotlin_modesto.RetrofitService
import com.example.tp_kotlin_modesto.response.QuoteResponse
import kotlinx.coroutines.launch


class MonoViewModel (
    private val retrofitService: RetrofitService
) : ViewModel(){

    private val _data = MutableLiveData<QuoteResponse>()
    val data: LiveData<QuoteResponse> = _data

    init {
        fetchData()
    }

    fun refresh() {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _data.value = retrofitService.getData().body()
        }
    }
}

class MonoViewModelFactory(
    private val retrofitService: RetrofitService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MonoViewModel::class.java)) {
            MonoViewModel(retrofitService) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
