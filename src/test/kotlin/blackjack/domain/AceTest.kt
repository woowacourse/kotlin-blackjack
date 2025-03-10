package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AceTest {
    @Test
    fun `Ace는 1 또는 11로 계산된다`() {
        assertThat(Ace.possibleValues).contains(1)
        assertThat(Ace.possibleValues).contains(11)
    }
}
