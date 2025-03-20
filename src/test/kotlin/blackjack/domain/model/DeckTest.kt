package blackjack.domain.model

import blackjack.domain.HEART_SIX
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드들에서 카드를 반환한다`() {
        val cards = listOf(HEART_SIX)
        val deck = Deck.from(cards)
        assertThat(deck.draw()).isEqualTo(HEART_SIX)
    }
}
