package domain

import domain.person.Decision
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DecisionTest {
    @Test
    fun `Decision 은 y 나 n 이어야 한다`() {
        assertAll(
            { assertThat(Decision.of(true)).isEqualTo(Decision.YES) },
            { assertThat(Decision.of(false)).isEqualTo(Decision.NO) },
        )
    }
}
