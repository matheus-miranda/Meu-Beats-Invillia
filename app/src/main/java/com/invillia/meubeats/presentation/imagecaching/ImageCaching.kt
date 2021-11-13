package com.invillia.meubeats.presentation.imagecaching

import android.content.Context
import android.widget.ImageView

interface ImageCaching {
    fun displayImage(context: Context, url: String, target: ImageView)
}