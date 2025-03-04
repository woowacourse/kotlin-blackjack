package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `덱은 카드를 가진다`() {
        val deck = Deck(listOf(Card(Suit.HEART, Rank.SIX)))
        assertThat(deck.cards).isEqualTo(listOf(Card(Suit.HEART, Rank.SIX)))
    }
}