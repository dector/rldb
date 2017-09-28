package io.github.dector.rldb.common.repositories

import io.github.dector.rldb.domain.Game
import io.github.dector.rldb.domain.Uuid

interface GamesRepository {

    fun getAll(): List<Game>

    fun getFavourite(): List<Game>

    fun byUuid(uuid: Uuid): Game?

    fun toggleFavourite(uuid: Uuid, onSucceed: (item: Game) -> Unit)
}