package io.github.dector.rldb.games_list.navigation

import io.github.dector.rldb.common.domain.Uuid

interface Navigation {

    fun gotoFavourites()

    fun gotoDetails(uuid: Uuid)
}