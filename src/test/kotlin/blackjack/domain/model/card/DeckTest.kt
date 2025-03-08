package blackjack.domain.model.card

import blackjack.domain.generator.ShuffledCardsGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드를 뽑을 수 있다`() {
        val card: Card = Card(CardNumber.ACE, Suit.CLUB)

        val deck = Deck(ShuffledCardsGenerator()).pop()

        assertThat(card).isInstanceOf(Card::class.java)
    }
}
