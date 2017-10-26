package io.github.dector.rldb.common.navigation

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import io.github.dector.rldb.common.domain.Uuid
import io.github.dector.rldb.details.view.controllers.ItemDetailsController
import io.github.dector.rldb.favourites.view.controllers.FavouritesController


class AppNavigation(
        private val router: Router) : Navigation {

    override fun gotoFavourites() {
        navigateTo(FavouritesController())
    }

    override fun gotoDetails(uuid: Uuid) {
        navigateTo(ItemDetailsController(uuid))
    }

    private fun navigateTo(controller: Controller) = router.pushController(transaction(controller))

    private fun transaction(controller: Controller) = RouterTransaction.with(controller)
            .pushChangeHandler(HorizontalChangeHandler())
            .popChangeHandler(HorizontalChangeHandler())
}