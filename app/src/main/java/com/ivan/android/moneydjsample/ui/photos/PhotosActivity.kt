package com.ivan.android.moneydjsample.ui.photos

import android.os.Bundle
import android.os.StrictMode
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.ivan.android.moneydjsample.R
import com.ivan.android.moneydjsample.data.DataRepository
import com.ivan.android.moneydjsample.net.Result
import kotlinx.android.synthetic.main.activity_photos.*


class PhotosActivity : AppCompatActivity() {

    private val viewModel by viewModels<PhotosViewModel> {
        PhotosViewModelFactory(DataRepository())
    }

    private val adapter by lazy { PhotosAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = ""
        }


        rv_photo_list.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = this@PhotosActivity.adapter
        }

        viewModel.result.observe(this) {
            when(it) {
                Result.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    layout_error.visibility = View.INVISIBLE
                }
                is Result.Success -> {

                    progressBar.visibility = View.INVISIBLE
                    layout_error.visibility = View.INVISIBLE
                    val data = it.data
                    adapter.setPhotoList(data)

                }
                is Result.Error -> {
                    progressBar.visibility = View.INVISIBLE
                    layout_error.visibility = View.VISIBLE
                }
            }
        }

        btn_reload.setOnClickListener {
            viewModel.getPhotosData()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}