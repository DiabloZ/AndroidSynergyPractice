package su.vi.androidsynergypractice.calc.di

import su.vi.androidsynergypractice.calc.CalculatorApi
import su.vi.androidsynergypractice.calc.domain.interactors.CalcInteractor
import su.vi.androidsynergypractice.calc.domain.use_cases.EvaluateUseCase
import su.vi.androidsynergypractice.calc.presentation.CalculatorImpl
import su.vi.androidsynergypractice.calc.presentation.main_screen.MainScreenViewModel
import su.vi.dimodule.DIModule
import su.vi.simply.di.core.entry_point.addDependencyLater
import su.vi.simply.di.core.entry_point.addDependencyNow
import su.vi.simply.di.core.entry_point.getDependency

abstract class CalcComponent {
    companion object {
        fun init() {
            DIModule.getContainer().apply {
                addDependencyLater<CalculatorApi> { CalculatorImpl() }
                addDependencyLater<MainScreenViewModel> {
                    MainScreenViewModel(
                        calcInteractor = getDependency<CalcInteractor>()
                    )
                }
                addDependencyLater<CalcInteractor> {
                    CalcInteractor(
                        evaluateUseCase = getDependency<EvaluateUseCase>()
                    )
                }
                addDependencyLater<EvaluateUseCase> { EvaluateUseCase() }
            }
        }
    }
}