package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드를 하나 뽑는다`() {
        val cardDeck = CardDeck(listOf(Card.of(13), Card.of(2)))
        assertThat(cardDeck.draw()).isEqualTo(Card.of(13))
    }
}
