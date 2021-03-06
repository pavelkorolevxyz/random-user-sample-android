package xyz.pavelkorolev.randomuser

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * Base Fragment abstraction
 */
abstract class BaseFragment constructor(
    @LayoutRes contentLayoutId: Int
) : Fragment(contentLayoutId) {

    constructor() : this(0)
}
