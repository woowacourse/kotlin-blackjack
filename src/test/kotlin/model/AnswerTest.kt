package model

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class AnswerTest {
    @ParameterizedTest
    @ValueSource(strings = ["Y", "y", "N", "n"])
    fun `y 또는 n 의 값을 보관한다`(input: String) {
        assertDoesNotThrow {
            Answer.fromInput(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["d", "A", "Z", "100"])
    fun `y 또는 n이 아니면 예외 발생`(input: String) {
        assertThatThrownBy {
            Answer.fromInput(input)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Answer.ERROR_INVALID_FORMAT)
    }
}
