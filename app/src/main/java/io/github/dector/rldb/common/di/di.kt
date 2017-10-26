package io.github.dector.rldb.common.di

import com.bluelinelabs.conductor.Router
import dagger.Component
import dagger.Module
import dagger.Provides
import io.github.dector.rldb.common.navigation.AppNavigation
import io.github.dector.rldb.common.navigation.Navigation
import io.github.dector.rldb.common.repositories.GamesRepository
import io.github.dector.rldb.common.repositories.InMemoryGamesRepository


@Component(modules = arrayOf(NavigationModule::class))
interface NavigationComponent

@Module
class NavigationModule(private val router: Router) {

    @Provides fun router(): Router
            = router

    @Provides fun navigation(router: Router): Navigation
            = AppNavigation(router)
}

@Module
class RepositoriesModule {

    @Provides fun games(): GamesRepository = InMemoryGamesRepository()
}