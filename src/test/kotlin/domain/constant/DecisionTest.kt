package domain.constant

import constant.Decision
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow

class DecisionTest {
    @Test
    fun `Decision 은 y 나 n 이어야 한다`() {
        assertAll(
            { assertDoesNotThrow { Decision.of("y") } },
            { assertDoesNotThrow { Decision.of("n") } },
            { assertThat(Decision.of("p")).isNull() },
            { assertThat(Decision.of("1")).isNull() },
        )
    }
}
