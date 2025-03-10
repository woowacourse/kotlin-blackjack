package blackjack.domain

import blackjack.domain.MultiValueRank.AceRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AceTest {
    @Test
    fun `Ace는 1 또는 11로 계산된다`() {
        assertThat(AceRank.possibleValues).contains(1)
        assertThat(AceRank.possibleValues).contains(11)
    }
}
