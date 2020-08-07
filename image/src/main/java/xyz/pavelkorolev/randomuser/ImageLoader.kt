package xyz.pavelkorolev.randomuser

import android.widget.ImageView
import coil.api.load
import coil.request.LoadRequestBuilder
import coil.transform.CircleCropTransformation

/**
 * Loads images.
 * External [ImageLoader] users can be library agnostic.
 */
class ImageLoader {

    /**
     * Loads image
     *
     * @param imageView view to load image in
     * @param image image to load
     * @param placeholderImage image to show when loading or if [image] is not available
     * @param crop crop mode for image
     */
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
