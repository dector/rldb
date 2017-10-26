package io.github.dector.rldb.common.navigation

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import io.github.dector.rldb.common.domain.Uuid
import io.github.dector.rldb.details.view.controllers.ItemDetailsController
import io.github.dector.rldb.favourites.view.controllers.FavouritesController


class AppNavigation(
        private val router: Router) : Navigation {

    override fun gotoFavourites() {
        router.pushController(RouterTransaction.with(FavouritesController()))
    }

    override fun gotoDetails(uuid: Uuid) {
        router.pushController(RouterTransaction.with(ItemDetailsController(uuid)))
    }
}