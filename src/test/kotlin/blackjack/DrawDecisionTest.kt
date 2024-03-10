package blackjack

import blackjack.model.DrawDecision
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DrawDecisionTest {
    @ParameterizedTest
    @CsvSource("y", "n")
    fun `올바른 드로우 의사 입력`(input: String) {
        assertDoesNotThrow { DrawDecision(input) }
    }

    @ParameterizedTest
    @CsvSource("Y", "N", "yes", "no", "네", "아니오")
    fun `드로우 의사 입력 예외 처리`(input: String) {
        assertThrows<IllegalArgumentException> { DrawDecision(input) }
    }

    @Test
    fun `드로우 의사 입력값이 y일때 true 반환 확인`() {
        val drawDecision = DrawDecision("y")

        val actual = drawDecision.judgeDecision()

        assertThat(actual).isTrue
    }

    @Test
    fun `드로우 의사 입력값이 n일때 false 반환 확인`() {
        val drawDecision = DrawDecision("n")

        val actual = drawDecision.judgeDecision()

        assertThat(actual).isFalse
    }
}
