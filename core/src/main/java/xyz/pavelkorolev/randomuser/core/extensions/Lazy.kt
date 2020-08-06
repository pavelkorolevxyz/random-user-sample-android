package xyz.pavelkorolev.randomuser.core.extensions

/**
 * Non synchronized lazy implementation
 */
fun <T> lazyUi(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
