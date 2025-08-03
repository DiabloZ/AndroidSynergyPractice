package su.vi.androidsynergypractice.calc.domain

// Operators
internal const val OP_PLUS = "+"
internal const val OP_MINUS = "-"
internal const val OP_MULTIPLY = "*"
internal const val OP_DIVIDE = "/"
internal const val OP_MODULO = "%"
internal const val OP_POWER = "^"

// Functions
internal const val FUNC_SIN = "sin"
internal const val FUNC_COS = "cos"
internal const val FUNC_TAN = "tan"
internal const val FUNC_SQRT = "sqrt"
internal const val FUNC_LOG10 = "log" // Assuming "log" means log10
internal const val FUNC_LN = "ln"
internal const val FUNC_ABS = "abs"

// Parentheses
internal const val PAREN_OPEN = "("
internal const val PAREN_CLOSE = ")"

// Regex
internal const val TOKENIZE_REGEX_PATTERN = """\d+(\.\d+)?|[()+\-*/^%]|[a-zA-Z]+"""
internal const val WHITESPACE_REGEX_PATTERN = """\s"""

// Error Messages
internal const val ERROR_UNKNOWN_OPERATOR = "Unknown operator "
internal const val ERROR_UNKNOWN_FUNCTION = "Unknown function "
internal const val ERROR_INVALID_EXPRESSION = "Invalid expression"

// Operator Precedence
internal const val PRECEDENCE_LEVEL_1 = 1
internal const val PRECEDENCE_LEVEL_2 = 2
internal const val PRECEDENCE_LEVEL_3 = 3

// Evaluation Defaults
internal const val DEFAULT_EMPTY_RPN_RESULT = 0.0
internal const val WHOLE_NUMBER_CHECK_MODULUS = 1.0
internal const val WHOLE_NUMBER_CHECK_REMAINDER = 0.0

internal const val BLANK = ""