package su.vi.androidsynergypractice.calc.domain.use_cases

import su.vi.androidsynergypractice.calc.domain.BLANK
import su.vi.androidsynergypractice.calc.domain.DEFAULT_EMPTY_RPN_RESULT
import su.vi.androidsynergypractice.calc.domain.ERROR_INVALID_EXPRESSION
import su.vi.androidsynergypractice.calc.domain.ERROR_UNKNOWN_FUNCTION
import su.vi.androidsynergypractice.calc.domain.ERROR_UNKNOWN_OPERATOR
import su.vi.androidsynergypractice.calc.domain.FUNC_ABS
import su.vi.androidsynergypractice.calc.domain.FUNC_COS
import su.vi.androidsynergypractice.calc.domain.FUNC_LN
import su.vi.androidsynergypractice.calc.domain.FUNC_LOG10
import su.vi.androidsynergypractice.calc.domain.FUNC_SIN
import su.vi.androidsynergypractice.calc.domain.FUNC_SQRT
import su.vi.androidsynergypractice.calc.domain.FUNC_TAN
import su.vi.androidsynergypractice.calc.domain.OP_DIVIDE
import su.vi.androidsynergypractice.calc.domain.OP_MINUS
import su.vi.androidsynergypractice.calc.domain.OP_MODULO
import su.vi.androidsynergypractice.calc.domain.OP_MULTIPLY
import su.vi.androidsynergypractice.calc.domain.OP_PLUS
import su.vi.androidsynergypractice.calc.domain.OP_POWER
import su.vi.androidsynergypractice.calc.domain.PAREN_CLOSE
import su.vi.androidsynergypractice.calc.domain.PAREN_OPEN
import su.vi.androidsynergypractice.calc.domain.PRECEDENCE_LEVEL_1
import su.vi.androidsynergypractice.calc.domain.PRECEDENCE_LEVEL_2
import su.vi.androidsynergypractice.calc.domain.PRECEDENCE_LEVEL_3
import su.vi.androidsynergypractice.calc.domain.TOKENIZE_REGEX_PATTERN
import su.vi.androidsynergypractice.calc.domain.WHITESPACE_REGEX_PATTERN
import su.vi.androidsynergypractice.calc.domain.WHOLE_NUMBER_CHECK_MODULUS
import su.vi.androidsynergypractice.calc.domain.WHOLE_NUMBER_CHECK_REMAINDER
import su.vi.androidsynergypractice.calc.domain.utils.pollLastKt
import java.util.LinkedList
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

internal class EvaluateUseCase() {
    operator fun invoke(expr: String): Result<Number> {
        val result = runCatching {
            val tokens = tokenize(expr)
            val rpn = toRPN(tokens)
            evalRPN(rpn)
        }

        return result
    }

    private val operators = mapOf(
        OP_PLUS to PRECEDENCE_LEVEL_1,
        OP_MINUS to PRECEDENCE_LEVEL_1,
        OP_MULTIPLY to PRECEDENCE_LEVEL_2,
        OP_DIVIDE to PRECEDENCE_LEVEL_2,
        OP_MODULO to PRECEDENCE_LEVEL_2,
        OP_POWER to PRECEDENCE_LEVEL_3
    )

    private val functions = setOf(FUNC_SIN, FUNC_COS, FUNC_TAN, FUNC_SQRT, FUNC_LOG10, FUNC_LN, FUNC_ABS)

    private fun tokenize(expr: String): List<String> {
        val regex = Regex(TOKENIZE_REGEX_PATTERN)
        return regex.findAll(expr.replace(WHITESPACE_REGEX_PATTERN.toRegex(), BLANK)).map { it.value }.toList()
    }

    private fun toRPN(tokens: List<String>): List<String> {
        val output = mutableListOf<String>()
        val stack = mutableListOf<String>()

        for (token in tokens) {
            when {
                token.toDoubleOrNull() != null -> output.add(token)

                token in functions -> stack.add(token)

                token in operators -> {
                    while (
                        stack.isNotEmpty() &&
                        stack.last() in operators &&
                        operators[token]!! <= operators[stack.last()]!!
                    ) {
                        output.add(stack.removeAt(stack.size - 1))
                    }
                    stack.add(token)
                }

                token == PAREN_OPEN -> stack.add(token)

                token == PAREN_CLOSE -> {
                    while (stack.isNotEmpty() && stack.last() != PAREN_OPEN) {
                        output.add(stack.removeAt(stack.size - 1))
                    }
                    if (stack.isNotEmpty() && stack.last() == PAREN_OPEN) {
                        stack.removeAt(stack.size - 1)
                    }
                    if (stack.isNotEmpty() && stack.last() in functions) {
                        output.add(stack.removeAt(stack.size - 1))
                    }
                }
            }
        }

        while (stack.isNotEmpty()) {
            output.add(stack.removeAt(stack.size - 1))
        }

        return output
    }

    private fun evalRPN(rpn: List<String>): Number {
        if (rpn.isEmpty()) {
            return DEFAULT_EMPTY_RPN_RESULT
        }
        val stack1 = LinkedList<Double>()

        for (token in rpn) {
            when {
                token.toDoubleOrNull() != null -> stack1.add(token.toDouble())

                token in operators -> {
                    val b = stack1.pollLastKt()
                    val a = stack1.pollLastKt()
                    val res = when (token) {
                        OP_PLUS -> a + b
                        OP_MINUS -> a - b
                        OP_MULTIPLY -> a * b
                        OP_DIVIDE -> a / b
                        OP_MODULO -> a % b
                        OP_POWER -> a.pow(b)
                        else -> error("$ERROR_UNKNOWN_OPERATOR$token")
                    }
                    stack1.add(res)
                }

                token in functions -> {
                    val a = stack1.pollLastKt()
                    val res = when (token) {
                        FUNC_SIN -> sin(Math.toRadians(a))
                        FUNC_COS -> cos(Math.toRadians(a))
                        FUNC_TAN -> tan(Math.toRadians(a))
                        FUNC_SQRT -> sqrt(a)
                        FUNC_LOG10 -> log10(a)
                        FUNC_LN -> ln(a)
                        FUNC_ABS -> abs(a)
                        else -> error("$ERROR_UNKNOWN_FUNCTION$token")
                    }
                    stack1.add(res)
                }
            }
        }

        val evalRes = stack1.singleOrNull() ?: error(ERROR_INVALID_EXPRESSION)
        return if (evalRes % WHOLE_NUMBER_CHECK_MODULUS == WHOLE_NUMBER_CHECK_REMAINDER) {
            evalRes.toInt()
        } else {
            evalRes
        }
    }
}