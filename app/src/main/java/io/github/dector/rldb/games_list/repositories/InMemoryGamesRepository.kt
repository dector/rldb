package io.github.dector.rldb.games_list.repositories

import io.github.dector.rldb.common.repositories.GamesRepository
import io.github.dector.rldb.domain.Game

class InMemoryGamesRepository : GamesRepository {

    override fun getAll() = listOf(
            Game(
                    name = "Nethack",
                    description = "Nethack RL",
                    imageUrl = "https://i.imgur.com/GOIK6wG.png"
            ),
            Game(
                    name = "Brogue",
                    description = "Brogue RL",
                    imageUrl = "https://i.imgur.com/IOqX1YS.png"
            )
    )
}