package su.vi.dimodule

import android.app.Application
import su.vi.simply.di.core.SimplyDIContainer
import su.vi.simply.di.core.entry_point.initialize

object DIModule {

    private lateinit var container : SimplyDIContainer

    fun initialize(application: Application) {
        container = SimplyDIContainer.initialize {
            addDependencyNow<Application> { application }
        }
    }

    fun getContainer(): SimplyDIContainer = container
}