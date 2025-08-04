package su.vi.androidsynergypractice.calc

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class CalculatorImpl : CalculatorApi {
    @Composable
    override fun CalculatorScreen(modifier: Modifier) {
        su.vi.androidsynergypractice.calc.presentation.main_screen.CalculatorScreen(modifier = modifier)
    }
}
