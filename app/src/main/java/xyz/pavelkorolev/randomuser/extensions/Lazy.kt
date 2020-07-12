package xyz.pavelkorolev.randomuser.extensions

fun <T> lazyUi(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
