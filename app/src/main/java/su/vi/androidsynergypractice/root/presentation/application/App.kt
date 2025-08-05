package su.vi.androidsynergypractice.root.presentation.application

import android.app.Application
import su.vi.androidsynergypractice.calc.di.CalcComponent
import su.vi.dimodule.DIModule

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        DIModule.initialize(this)
        CalcComponent.init()
    }
}