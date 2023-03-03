package domain.constant

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class DecisionTest {
    @Test
    fun `Decision 은 y 나 n 이어야 한다`() {
        assertAll(
            { assertDoesNotThrow { Decision.of("y") } },
            { assertDoesNotThrow { Decision.of("n") } },
            { assertThrows<IllegalArgumentException> { Decision.of("p") } },
            { assertThrows<IllegalArgumentException> { Decision.of("1") } },
        )
    }

    @Test
    fun `Decision에 해당 text가 존재함`() {
        assertAll(
            { assertThat(Decision.has("y")).isTrue() },
            { assertThat(Decision.has("n")).isTrue() },
            { assertThat(Decision.has("a")).isFalse() },
            { assertThat(Decision.has("b")).isFalse() },
        )
    }
}
