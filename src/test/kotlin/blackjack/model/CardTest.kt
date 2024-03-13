package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `각 카드의 계급에 따라 올바른 점수를 반환하는지 테스트`() {
        Denomination.entries.forEach {
            val card = Card(it, Suit.SPADE)
            assertThat(card.denomination.score).isEqualTo(it.score)
        }
    }

    @Test
    fun `각 카드의 suit에 따라 올바른 suit 값을 반환하는지 테스트 `() {
        Suit.entries.forEach {
            val card = Card(Denomination.ACE, it)
            assertThat(card.suit).isEqualTo(it)
        }
    }
}
