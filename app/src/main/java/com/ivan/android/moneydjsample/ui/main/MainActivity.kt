package com.ivan.android.moneydjsample.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.ivan.android.moneydjsample.R
import com.ivan.android.moneydjsample.data.DataRepository
import kotlinx.android.synthetic.main.activity_main.*
import com.ivan.android.moneydjsample.net.Result
import com.ivan.android.moneydjsample.ui.photos.PhotosActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_request_api.setOnClickListener {
            startActivity(Intent(this, PhotosActivity::class.java))
        }

    }
}