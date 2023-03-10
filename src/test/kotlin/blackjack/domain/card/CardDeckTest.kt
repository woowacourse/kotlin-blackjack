package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드를 하나 뽑는다`() {
        val cardDeck = CardDeck(Card(CardNumber.KING, Suit.SPADE), Card(CardNumber.EIGHT, Suit.DIAMOND))
        assertThat(cardDeck.draw()).isEqualTo(Card(CardNumber.KING, Suit.SPADE))
    }
}
