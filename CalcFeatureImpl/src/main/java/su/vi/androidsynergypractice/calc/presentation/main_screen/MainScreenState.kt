package su.vi.androidsynergypractice.calc.presentation.main_screen

internal class MainScreenState(
    val evaluateString: String,
    val resultString: String,
) {
    internal companion object {
        val InitialState = MainScreenState(
            evaluateString = "",
            resultString = "",
        )
    }
}