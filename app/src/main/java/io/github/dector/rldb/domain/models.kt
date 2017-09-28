package io.github.dector.rldb.domain

import java.util.*


typealias Uuid = String

data class Game(
        val uuid: String,
        val name: String,
        val description: String,
        val imageUrl: String? = null,
        val codingLanguage: String = "",
        val platforms: List<Platform> = emptyList(),
        val lastVersion: String = "",
        val lastVersionDate: Date? = null)

enum class Platform {
    Windows, Linux, MacOS, Android, iOS
}