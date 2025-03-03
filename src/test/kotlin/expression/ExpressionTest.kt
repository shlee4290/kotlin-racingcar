package expression

import calculator.Expression
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ExpressionTest {
    @Test
    fun 덧셈() {
        assertThat(Expression("2 + 3").calculate()).isEqualTo(5f)
    }

    @Test
    fun 뺄셈() {
        assertThat(Expression("2 - 3").calculate()).isEqualTo(-1f)
    }

    @Test
    fun 곱셈() {
        assertThat(Expression("2 * 3").calculate()).isEqualTo(6f)
    }

    @Test
    fun 나눗셈() {
        assertThat(Expression("2 / 3").calculate()).isEqualTo(2f / 3f)
    }

    @Test
    fun `사칙연산 여러개 포함하는 식 계산`() {
        assertThat(Expression("2 + 3 * 4 / 2").calculate()).isEqualTo(10f)
    }

    @Test
    fun ` 피연산자 하나만 있는 식 계산`() {
        assertThat(Expression("2").calculate()).isEqualTo(2f)
    }

    @Test
    fun `입력값이 공백 문자일 경우 throw IllegalArgumentException`() {
        assertThatIllegalArgumentException().isThrownBy {
            Expression("")
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1 % 3", "abc"])
    fun `입력값에 숫자 or 사칙연산 기호 이외 문자가 섞인 경우 throw IllegalArgumentException`(expressionString: String) {
        assertThatIllegalArgumentException().isThrownBy {
            Expression(expressionString)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["2 +", "*", "3 3 +"])
    fun `입력값의 연산자와 피연산자 순서가 잘못된 경우 throw IllegalArgumentException`(expressionString: String) {
        assertThatIllegalArgumentException().isThrownBy {
            Expression(expressionString)
        }
    }
}
