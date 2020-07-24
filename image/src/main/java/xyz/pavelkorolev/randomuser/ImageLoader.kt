package xyz.pavelkorolev.randomuser

import android.widget.ImageView
import coil.api.load
import coil.request.LoadRequestBuilder
import coil.transform.CircleCropTransformation

class ImageLoader {

    fun load(
        imageView: ImageView,
        image: Image,
        placeholderImage: Image.Resource? = null,
        crop: ImageCrop = ImageCrop.None
    ) {
        val builder: LoadRequestBuilder.() -> Unit = {
            placeholderImage?.let { placeholder(it.resId) }
            if (crop is ImageCrop.Circle) {
                transformations(CircleCropTransformation())
            }
        }
        when (image) {
            is Image.Url -> imageView.load(image.url, builder = builder)
            is Image.Resource -> imageView.load(image.resId, builder = builder)
        }
    }
}
