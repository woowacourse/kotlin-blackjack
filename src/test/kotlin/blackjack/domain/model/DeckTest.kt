package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Deck
import blackjack.domain.model.card.Rank
import blackjack.domain.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱에서 카드를 반환한다`() {
        val deck = Deck(listOf(Card(Suit.HEART, Rank.SIX)))
        assertThat(deck.draw(1)).isEqualTo(listOf(Card(Suit.HEART, Rank.SIX)))
    }
}
