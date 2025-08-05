package su.vi.androidsynergypractice.calc.di

import su.vi.androidsynergypractice.calc.CalculatorApi
import su.vi.androidsynergypractice.calc.presentation.CalculatorImpl
import su.vi.dimodule.DIModule
import su.vi.simply.di.core.entry_point.addDependencyNow

abstract class CalcComponent {
    companion object {
        fun init(){
            DIModule.getContainer().apply{
                addDependencyNow<CalculatorApi> { CalculatorImpl() }
            }
        }
    }
}