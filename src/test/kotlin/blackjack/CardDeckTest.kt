package blackjack

import blackjack.model.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드 뭉치 생성`() {
        val deck = CardDeck()

        val actual = deck.cards

        assertThat(actual.size).isEqualTo(52)
    }

    @Test
    fun `카드 한장 뽑아서 제거`() {
        val deck = CardDeck()

        deck.pickCard()

        assertThat(deck.cards.size).isEqualTo(51)
    }
}
