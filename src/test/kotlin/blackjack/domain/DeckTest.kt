package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱에서 카드를 한 장씩 뽑는다`() {
        val deck = Deck.create()
        val dealer = Dealer()

        dealer.receive(deck.draw())

        assertThat(dealer.cards.size).isEqualTo(1)
    }
}
