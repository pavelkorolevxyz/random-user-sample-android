@file:DependsOn("io.github.ackeecz:danger-kotlin-detekt:0.1.4")

import io.github.ackeecz.danger.detekt.DetektPlugin
import systems.danger.kotlin.danger
import systems.danger.kotlin.onGitHub
import systems.danger.kotlin.register
import systems.danger.kotlin.warn
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

register plugin DetektPlugin

danger(args) {
    onGitHub {
        if (pullRequest.title.contains("WIP", false)) {
            warn("PR is classed as Work in Progress")
        }
    }

    val detektReports = Files.find(Paths.get(""), 10, { path, _ ->
        val fileName = path.toFile().name
        fileName.endsWith("detekt.xml")
    }).map { it.toFile() }.collect(Collectors.toList())

    DetektPlugin.parseAndReport(*detektReports.toTypedArray())
}
