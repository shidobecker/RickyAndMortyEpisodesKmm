package br.com.shido.rickyandmortyepisodeskmm.android.extensions

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

fun Context.getImageByName(name: String?): ImageBitmap {
    val resourceId: Int = resources
        .getIdentifier(name, "drawable", packageName)
    val drawable = resources.getDrawable(resourceId)
    return (drawable as BitmapDrawable).bitmap.asImageBitmap()
}