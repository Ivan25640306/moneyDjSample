package com.ivan.android.moneydjsample.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
import com.ivan.android.moneydjsample.R
import com.ivan.android.moneydjsample.data.PhotoData
import kotlinx.android.synthetic.main.activity_photo_detail.*
import kotlinx.android.synthetic.main.activity_photo_detail.toolbar
import kotlinx.android.synthetic.main.activity_photos.*

class PhotoDetailActivity : AppCompatActivity() {
    var data:PhotoData? = null

    companion object {
        const val ATTR_PHOTO_DATA = "attr_photo_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = ""
        }

        data = intent.getSerializableExtra(ATTR_PHOTO_DATA) as PhotoData?

        data?.let {
            val settings: WebSettings = wv_thumbnail.settings
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL


            wv_thumbnail.loadUrl(it.url)

            tv_id.text = it.id.toString()
            tv_title.text = it.title
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