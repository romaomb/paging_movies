package codes.romao.paging_movies.core

import android.app.Application
import codes.romao.paging_movies.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApp)
            modules(appModules)
        }
    }
}
