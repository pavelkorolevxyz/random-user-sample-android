package xyz.pavelkorolev.randomuser.about.domain

import xyz.pavelkorolev.randomuser.about.data.VersionRepository
import xyz.pavelkorolev.randomuser.core.model.Try
import javax.inject.Inject

/**
 * Loads application version name
 */
class LoadVersionUseCase @Inject constructor(
    private val versionRepository: VersionRepository
) {

    operator fun invoke(): Try<String> {
        return versionRepository.loadVersion()
    }
}
