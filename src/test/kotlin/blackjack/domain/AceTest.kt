package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AceTest {
    @Test
    fun `Ace는 1 또는 11로 계산된다`() {
        val ace = Ace()
        assertThat(ace.possibleValues).contains(1)
        assertThat(ace.possibleValues).contains(11)
    }
}
