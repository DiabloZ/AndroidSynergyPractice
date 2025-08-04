package su.vi.androidsynergypractice.calc.presentation.main_screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import su.vi.androidsynergypractice.calc.domain.BLANK
import su.vi.androidsynergypractice.calc.domain.interactors.CalcInteractor

internal class MainScreenViewModel(
    private val calcInteractor: CalcInteractor
): ViewModel() {

    private val _state = MutableStateFlow<MainScreenState>(MainScreenState.InitialState)
    internal val state = _state.asStateFlow()

    fun sendAction(action: MainScreenActions) {
        when(action) {
            is MainScreenActions.Evaluate -> evaluate(action.expression)
        }
    }

    private fun evaluate(expression: String) {
        val result = calcInteractor.evaluate(expression)
        val resultString = result.getOrNull()?.toString() ?: result.exceptionOrNull()?.localizedMessage ?: BLANK

        _state.value = MainScreenState(
            evaluateString = expression,
            resultString = resultString
        )
    }

}