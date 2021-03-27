package com.ivan.android.moneydjsample.ui.photos

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.ivan.android.moneydjsample.R
import com.ivan.android.moneydjsample.data.PhotoData
import com.ivan.android.moneydjsample.ui.detail.PhotoDetailActivity
import com.ivan.android.moneydjsample.ui.detail.PhotoDetailActivity.Companion.ATTR_PHOTO_DATA
import kotlinx.android.synthetic.main.view_item_photo.view.*
import java.io.Serializable


class PhotosAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val photoList = ArrayList<PhotoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoViewHolder) {
            holder.updateData(photoList[position])
        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    fun setPhotoList(list: List<PhotoData>) {
        photoList.clear()
        photoList.addAll(list)
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var data: PhotoData? = null

        init {

            val settings: WebSettings = itemView.wv_photo.settings
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.blockNetworkImage = true
            settings.domStorageEnabled = true
            settings.databaseEnabled = true
            settings.allowFileAccess = true;


            itemView.view_click_layer.setOnClickListener {
                clickAction()
            }
        }

        private fun clickAction() {
            data?.let { photoData ->
                itemView.context.startActivity(
                    Intent(
                        itemView.context,
                        PhotoDetailActivity::class.java
                    ).apply {
                        putExtra(ATTR_PHOTO_DATA, photoData as Serializable?)
                    })
            }
        }

        fun updateData(data: PhotoData) {
            this.data = data
            updateView()
        }

        private fun updateView() {
            data?.let {
                itemView.tv_id.text = it.id.toString()
                itemView.wv_photo.loadUrl(it.thumbnailUrl)
                itemView.tv_title.text = it.title
            }

        }
    }
}