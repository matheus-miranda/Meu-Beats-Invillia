package com.invillia.meubeats.presentation.imagecaching

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageCachingImpl : ImageCaching {
    override fun displayImage(context: Context, url: String, target: ImageView) {
        Glide.with(context).load(url).into(target)
    }
}