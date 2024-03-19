package blackjack.model.participant

import blackjack.model.deck.Deck
import blackjack.testmachine.NormalCardMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `초기 카드의 개수는 2이다`() {
        val deck = Deck(NormalCardMachine())
        val dealer = Dealer.withInitCards(deck::draw)
        assertThat(dealer.getAllCards().size).isEqualTo(INIT_CARD_SIZE)
    }

    companion object {
        private const val INIT_CARD_SIZE = 2
    }
}
