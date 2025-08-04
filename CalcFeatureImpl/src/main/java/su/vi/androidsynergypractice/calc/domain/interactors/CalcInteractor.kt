package su.vi.androidsynergypractice.calc.domain.interactors

import su.vi.androidsynergypractice.calc.domain.use_cases.EvaluateUseCase

internal class CalcInteractor(
    val evaluateUseCase: EvaluateUseCase
) {
    internal fun evaluate(expression: String): Result<Number> {
        return evaluateUseCase.invoke(expression)
    }

}