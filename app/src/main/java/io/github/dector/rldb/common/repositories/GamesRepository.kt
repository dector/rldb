package io.github.dector.rldb.common.repositories

import io.github.dector.rldb.domain.Game
import io.github.dector.rldb.domain.Uuid

interface GamesRepository {

    fun getAll(): List<Game>

    fun byUuid(uuid: Uuid): Game?
}