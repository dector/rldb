package io.github.dector.rldb

import android.app.Application
import com.orhanobut.logger.Logger
import io.github.dector.rldb.common.tools.LoggerTree
import io.github.dector.rldb.common.tools.createLogAdapter
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initLogger()
    }

    private fun initLogger() {
        Logger.addLogAdapter(createLogAdapter())

        Timber.plant(LoggerTree())
    }
}