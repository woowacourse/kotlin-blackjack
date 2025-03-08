package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Deck
import blackjack.domain.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드를 뽑을 수 있다`() {
        val card: Card = Card(CardNumber.ACE, Suit.CLUB)

        val deck = Deck { listOf(card) }.pop()

        assertThat(card).isInstanceOf(Card::class.java)
    }
}
