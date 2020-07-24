package xyz.pavelkorolev.randomuser

sealed class ImageCrop {
    object None : ImageCrop()
    object Circle : ImageCrop()
}
