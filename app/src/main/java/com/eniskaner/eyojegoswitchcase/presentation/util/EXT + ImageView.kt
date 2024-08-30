package com.eniskaner.eyojegoswitchcase.presentation.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun ImageView.load(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

fun ImageView.loadCircleCrop(url: String) {
    Glide.with(this.context)
        .load(url)
        .circleCrop()
        .into(this)
}

fun ImageView.loadCenterCrop(url: String) {
    Glide.with(this.context)
        .load(url)
        .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(80)))
        .into(this)
}