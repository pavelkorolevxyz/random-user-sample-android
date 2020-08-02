package xyz.pavelkorolev.randomuser.about.domain

import xyz.pavelkorolev.randomuser.about.data.VersionRepository
import javax.inject.Inject

class LoadVersionUseCase @Inject constructor(
    private val versionRepository: VersionRepository
) {

    operator fun invoke(): Result<String> {
        return versionRepository.loadVersion()
    }
}
