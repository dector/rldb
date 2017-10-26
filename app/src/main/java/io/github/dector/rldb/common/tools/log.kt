package io.github.dector.rldb.common.tools

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.LogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import timber.log.Timber


inline fun i(msg: () -> String) {
    Timber.i(msg())
}

inline fun d(msg: () -> String) {
    Timber.d(msg())
}

inline fun v(msg: () -> String) {
    Timber.v(msg())
}

inline fun e(e: Throwable? = null, msg: () -> String) {
    Timber.e(e, msg())
}

fun createLogAdapter(): LogAdapter {
    val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .tag("RLDB")
            .build()

    return AndroidLogAdapter(formatStrategy)
}

class LoggerTree : Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
        Logger.log(priority, tag, message, t)
    }
}