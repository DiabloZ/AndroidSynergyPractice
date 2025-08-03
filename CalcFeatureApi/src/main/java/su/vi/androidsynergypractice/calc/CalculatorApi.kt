package su.vi.androidsynergypractice.calc

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface CalculatorApi {
    @Composable
    fun CalculatorScreen(modifier: Modifier = Modifier.Companion)
}