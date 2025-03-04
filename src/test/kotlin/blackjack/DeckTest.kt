package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `무작위 카드를 뽑을 수 있다`() {
        val deck = Deck()

        val pickedCard = deck.drawCard()

        assertThat(pickedCard).isInstanceOf(Card::class.java)
    }
}
