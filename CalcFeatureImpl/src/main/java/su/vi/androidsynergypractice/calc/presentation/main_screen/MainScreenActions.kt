package su.vi.androidsynergypractice.calc.presentation.main_screen

internal sealed interface MainScreenActions {
    class Evaluate(val expression: String) : MainScreenActions
}