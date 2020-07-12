package xyz.pavelkorolev.randomuser.core.extensions

fun <T> lazyUi(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
