package com.ivan.android.moneydjsample.data

import android.util.Log
import com.ivan.android.moneydjsample.net.NetService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import com.ivan.android.moneydjsample.net.Result
import com.ivan.android.moneydjsample.net.Result.Success
import com.ivan.android.moneydjsample.net.Result.Error
import com.ivan.android.moneydjsample.net.Result.Loading

class DataRepository (private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO){

    companion object {
        const val PHOTOS_URL = "https://jsonplaceholder.typicode.com/photos"
    }
    val netService = NetService()

    suspend fun getPhotosData(): Result<List<PhotoData>> = withContext(ioDispatcher){
        return@withContext netService.getNetWorkPhotosInfo(PHOTOS_URL)
    }

}