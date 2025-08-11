package su.vi.androidsynergypractice.calc.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import su.vi.androidsynergypractice.calc.domain.ExpressionEvaluator
import su.vi.simply.di.compose.simplyComposeViewModel

@Preview
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen()
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    val viewModel: MainScreenViewModel = simplyComposeViewModel<MainScreenViewModel>()

    fun evaluate(expression: String): String {
        viewModel.sendAction(
            MainScreenActions.Evaluate(expression = expression)
        )
        return ""/* try {
            val result = ExpressionEvaluator.evaluate(expression)
            result.toString()
        } catch (e: Exception) {
            e.message.toString()
        }*/
    }
    val state = viewModel.state.collectAsState()
    //var res by remember(input) { mutableStateOf(evaluate(input)) }
    val inputScroll = rememberScrollState()
    LaunchedEffect(input) {
        inputScroll.animateScrollTo(inputScroll.maxValue)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(inputScroll)
        ) {
            Text(
                text = input.ifEmpty { "0" },
                color = Color.White,
                fontSize = 48.sp,
                textAlign = TextAlign.End,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            )
        }

            Text(
                text = state.value.resultString,
                color = Color.White,
                fontSize = 22.sp,
                textAlign = TextAlign.End,
                softWrap = false,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            )



        val buttons = listOf(
            listOf("AC", "(", ")", "%"),
            listOf("sin", "cos", "tan", "^"),
            listOf("log", "ln", "√", "⌫"),
            listOf("7", "8", "9", "/"),
            listOf("4", "5", "6", "*"),
            listOf("1", "2", "3", "-"),
            listOf("0", ".", "=", "+")
        )

        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { label ->
                    CalculatorButton(label = label, onClick = {
                        when (label) {
                            "AC" -> input = ""
                            "⌫" -> if (input.isNotEmpty()) input = input.dropLast(1)
                            "=" -> input = evaluate(input)
                            else -> input += label
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(label: String, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(6.dp)
            .size(80.dp)
            .background(Color(0xFF2D2D2D), shape = MaterialTheme.shapes.medium)
            .clickable { onClick() }
    ) {
        Text(
            text = label,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
