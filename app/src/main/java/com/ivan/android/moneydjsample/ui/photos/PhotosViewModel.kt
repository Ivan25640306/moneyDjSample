package com.ivan.android.moneydjsample.ui.photos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ivan.android.moneydjsample.data.DataRepository
import com.ivan.android.moneydjsample.data.PhotoData
import com.ivan.android.moneydjsample.net.Result
import kotlinx.coroutines.launch

class PhotosViewModel(private val repository: DataRepository) : ViewModel(){
    private val _result: MutableLiveData<Result<List<PhotoData>>> = MutableLiveData<Result<List<PhotoData>>>()
    val result
        get() = _result

    init {
        getPhotosData()
    }


    fun getPhotosData() {
        viewModelScope.launch {
            _result.value = Result.Loading
            _result.value = repository.getPhotosData()
        }
    }

}

@Suppress("UNCHECKED_CAST")
class PhotosViewModelFactory (
        private val repository: DataRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            (PhotosViewModel(repository) as T)
}