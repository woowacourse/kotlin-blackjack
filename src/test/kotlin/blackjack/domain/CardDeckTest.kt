package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드를 하나 뽑는다`() {
        val cardDeck = CardDeck(listOf(Card(Suit.SPADE, CardNumber.KING), Card(Suit.DIAMOND, CardNumber.EIGHT)))
        assertThat(cardDeck.draw()).isEqualTo(Card(Suit.SPADE, CardNumber.KING))
    }
}
