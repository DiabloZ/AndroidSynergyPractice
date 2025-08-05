package su.vi.androidsynergypractice.root.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import su.vi.androidsynergypractice.calc.CalculatorApi
import su.vi.androidsynergypractice.ui.theme.AndroidSynergyPracticeTheme
import su.vi.dimodule.DIModule
import su.vi.simply.di.core.delegates.inject

class MainActivity : ComponentActivity() {
    private val calculatorApi: CalculatorApi by inject(DIModule.getContainer())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidSynergyPracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    calculatorApi.CalculatorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
