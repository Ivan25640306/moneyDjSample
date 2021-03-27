package com.ivan.android.moneydjsample.net

import android.util.Log
import com.ivan.android.moneydjsample.data.PhotoData
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import com.ivan.android.moneydjsample.net.Result
import com.ivan.android.moneydjsample.net.Result.Success
import com.ivan.android.moneydjsample.net.Result.Error
import com.ivan.android.moneydjsample.net.Result.Loading
import org.json.JSONArray
import org.json.JSONObject


class NetService {

    companion object {
        const val ATTR_ID = "id"
        const val ATTR_TITLE = "title"
        const val ATTR_THUMBNAIL_URL = "thumbnailUrl"
        const val ATTR_URL = "url"
    }

    fun getNetWorkPhotosInfo(urlPath: String): Result<List<PhotoData>> {
        val downloadStr = StringBuffer()
        val url: URL
        val con: URLConnection
        val input: InputStream
        var reader: BufferedReader? = null

        try {
            url = URL(urlPath)
            con = url.openConnection() // HttpURLConnection
            input = con.getInputStream()
            reader = BufferedReader(InputStreamReader(input))
            var line: String? = null // 暫存字串
            // 開始逐行讀字串
            while (reader.readLine().also { line = it } != null) {
                downloadStr.append(line)
            }
            return Success(analyzeData(downloadStr.toString()))

        } catch (e: MalformedURLException) {
            return Error(Exception("MalformedURLException"))

        } catch (e: IOException) {
            return Error(Exception("IOException"))

        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun analyzeData(dataStr: String): List<PhotoData> {
        val list = arrayListOf<PhotoData>()
        val jsonArray = JSONArray(dataStr)
        val jsonSize = jsonArray.length()
        for (index in 0 until jsonSize) {
            val jsonObj: JSONObject = jsonArray.getJSONObject(index)
            list.add(
                PhotoData(
                    jsonObj.getInt(ATTR_ID),
                    jsonObj.getString(ATTR_TITLE),
                    jsonObj.getString(ATTR_THUMBNAIL_URL),
                    jsonObj.getString(ATTR_URL)
                )
            )
        }

        return list
    }
}