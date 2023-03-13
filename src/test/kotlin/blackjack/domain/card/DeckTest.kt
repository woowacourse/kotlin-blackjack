package blackjack.domain.card

import blackjack.domain.participant.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class DeckTest {
    @Test
    fun `덱에서 카드를 한 장씩 뽑는다`() {
        val deck = Deck.create()
        val dealer = Dealer()

        dealer.receive(deck.draw())

        assertThat(dealer.cards.size).isEqualTo(1)
    }

    @Test
    fun `덱에서 52장을 모두 뽑으면 덱이 다시 채워진다`() {
        val deck = Deck.create()
        val dealer = Dealer()

        repeat(52) {
            dealer.receive(deck.draw())
        }

        assertDoesNotThrow {
            dealer.receive(deck.draw())
        }
    }
}
