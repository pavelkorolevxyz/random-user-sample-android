package xyz.pavelkorolev.randomuser

/**
 * Crop mode for image loading
 */
sealed class ImageCrop {

    /**
     * No crop at all
     */
    object None : ImageCrop()

    /**
     * Crop image as circle
     */
    object Circle : ImageCrop()
}
