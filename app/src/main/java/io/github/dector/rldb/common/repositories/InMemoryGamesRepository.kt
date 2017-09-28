package io.github.dector.rldb.common.repositories

import io.github.dector.rldb.domain.Game
import io.github.dector.rldb.domain.Uuid

class InMemoryGamesRepository : GamesRepository {

    companion object {

        private val storage = mutableListOf(
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
    }

    override fun getAll() = storage

    override fun getFavourite() = storage
            .filter { it.metaFavourite }

    override fun byUuid(uuid: Uuid) = storage
            .filter { it.uuid == uuid }
            .firstOrNull()

    override fun toggleFavourite(uuid: Uuid, onSucceed: (item: Game) -> Unit) {
        val storedItem = byUuid(uuid) ?: return

        val itemIndex = storage.indexOf(storedItem)

        val newItem = storedItem.copy(metaFavourite = !storedItem.metaFavourite)
        storage[itemIndex] = newItem

        onSucceed(newItem)
    }
}