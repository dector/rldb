package io.github.dector.rldb.games_list.navigation

import io.github.dector.rldb.domain.Uuid

interface Navigation {

    fun gotoFavourites()

    fun gotoDetails(uuid: Uuid)
}