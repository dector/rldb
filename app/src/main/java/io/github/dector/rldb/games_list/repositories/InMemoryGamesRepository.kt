package io.github.dector.rldb.games_list.repositories

import io.github.dector.rldb.common.repositories.GamesRepository
import io.github.dector.rldb.domain.Game
import io.github.dector.rldb.domain.Uuid

class InMemoryGamesRepository : GamesRepository {

    private val storage = listOf(
            Game(
                    uuid = "40bc5462-b7dd-4dcf-9141-1f26885c0f33",
                    name = "Nethack",
                    description = "Nethack RL",
                    imageUrl = "https://i.imgur.com/GOIK6wG.png"
            ),
            Game(
                    uuid = "4228f604-ab6d-46ab-b839-5bbc73d87ca5",
                    name = "Brogue",
                    description = "Brogue RL",
                    imageUrl = "https://i.imgur.com/IOqX1YS.png"
            )
    )

    override fun getAll() = storage

    override fun byUuid(uuid: Uuid) = storage.filter { it.uuid == uuid }.firstOrNull()
}