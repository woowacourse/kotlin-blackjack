package blackjack

import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `무작위 카드를 뽑을 수 있다`() {
        val deck = Deck()

        val card = deck.drawCard()

        assertThat(card).isInstanceOf(Card::class.java)
    }
}
