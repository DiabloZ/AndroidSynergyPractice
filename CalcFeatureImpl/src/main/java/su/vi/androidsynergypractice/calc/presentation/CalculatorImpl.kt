package su.vi.androidsynergypractice.calc.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import su.vi.androidsynergypractice.calc.CalculatorApi

class CalculatorImpl : CalculatorApi {
    @Composable
    override fun CalculatorScreen(modifier: Modifier) {
        su.vi.androidsynergypractice.calc.presentation.main_screen.CalculatorScreen(modifier = modifier)
    }
}