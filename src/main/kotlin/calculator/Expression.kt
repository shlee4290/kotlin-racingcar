package calculator

class Expression(expression: String) {
    private val elements: MathElements

    init {
        elements = MathElements(expression.toElements())
        requireValidExpression()
    }

    fun calculate(): Float {
        return elements.calculate()
    }

    private fun requireValidExpression() {
        require(elements.isValid())
    }

    private fun String.toElements(): List<MathElement> {
        fun String.splitBySpace() = split(" ")

        return splitBySpace().map {
            it.toMathElement()
        }
    }

    private fun String.toMathElement(): MathElement {
        if (Operator.isOperatorSymbol(this)) {
            return Operator.from(this)
        }
        return Operand.from(this)
    }
}
