package com.mobillium.movieapp.feature_movie.presentation.common.extension

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mobillium.movieapp.R

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showGenericAlertDialog(message: String) {
    AlertDialog.Builder(this).apply {
        setMessage(message)
        setPositiveButton(getString(R.string.button_text_ok)) { dialog, _ ->
            dialog.dismiss()
        }
    }.show()
}

fun Context.loadImageFromURL(image: ImageView, url: String) =
    Glide.with(this.applicationContext)
        .load(url)
        .error(R.mipmap.great_beauty)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .dontAnimate()
        .into(image)

fun Context.clearImageWithGlide(image: ImageView) =
    Glide.with(this)
        .clear(image)

fun Context.clearGlideMemory() = Thread {
    Glide.get(this).clearDiskCache()
}.start()

fun Context.clearDiskCache() = Glide.get(this).clearMemory()