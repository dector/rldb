package io.github.dector.rldb.di

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import dagger.Component
import dagger.Module
import dagger.Provides
import io.github.dector.rldb.common.repositories.GamesRepository
import io.github.dector.rldb.common.repositories.InMemoryGamesRepository
import io.github.dector.rldb.details.view.controllers.ItemDetailsController
import io.github.dector.rldb.domain.Uuid
import io.github.dector.rldb.favourites.view.controllers.FavouritesController
import io.github.dector.rldb.games_list.navigation.Navigation


@Component(modules = arrayOf(NavigationModule::class))
interface NavigationComponent

@Module
class NavigationModule(private val router: Router) {

    @Provides fun router(): Router
            = router

    @Provides fun navigation(router: Router): Navigation
            = object : Navigation {

        override fun gotoFavourites() {
            router.pushController(RouterTransaction.with(FavouritesController()))
        }

        override fun gotoDetails(uuid: Uuid) {
            router.pushController(RouterTransaction.with(ItemDetailsController(uuid)))
        }
    }
}

@Module
class RepositoriesModule {

    @Provides fun games(): GamesRepository = InMemoryGamesRepository()
}