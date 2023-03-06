package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackCardDeckTest {
    @Test
    fun `카드뭉치에서 두 장의 카드를 얻을 수 있다`() {
        val deck = BlackJackCardDeck()
        val cards = deck.drawInitCards()
        val expected = 2
        assertThat(cards.size).isEqualTo(expected)
    }
}
