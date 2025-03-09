package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드들에서 카드를 반환한다`() {
        val deck = Deck(listOf(Card(Suit.HEART, Rank.SIX)))
        assertThat(deck.draw(1)).isEqualTo(listOf(Card(Suit.HEART, Rank.SIX)))
    }

    @Test
    fun `카드들에서 카드 2장을 반환한다`() {
        val deck = Deck(listOf(Card(Suit.HEART, Rank.SIX), Card(Suit.HEART, Rank.SEVEN)))
        assertThat(deck.draw(1)).isEqualTo(listOf(Card(Suit.HEART, Rank.SIX), Card(Suit.HEART, Rank.SEVEN)))
    }
}
