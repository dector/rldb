package io.github.dector.rldb.common.repositories

import io.github.dector.rldb.domain.Game

interface GamesRepository {

    fun getAll(): List<Game>
}