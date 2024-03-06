package blackjack

import blackjack.model.card.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `Card의 개수가 52개인지 확인`() {
        val deck = Deck()
        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `카드 한장 빼기`() {
        val deck = Deck()
        deck.dealCard()
        deck.dealCard()
        assertThat(deck.cards.size).isEqualTo(50)
    }
}
