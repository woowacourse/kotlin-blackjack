package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드를 뽑을 수 있다`() {
        val card = Deck.giveCard()

        assertThat(card).isInstanceOf(Card::class.java)
    }
}
