package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드들에서 카드를 반환한다`() {
        val cards = listOf(Card(Suit.HEART, Rank.SIX))
        val deck = Deck(cards)
        assertThat(deck.draw()).isEqualTo(Card(Suit.HEART, Rank.SIX))
    }
}
