package blackjack.model.deck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `랜덤한 두장의 카드를 나눠준다`() {
        val deck = Deck()
        val cards = deck.draw(2)
        assertThat(cards.size).isEqualTo(2)
    }
}
