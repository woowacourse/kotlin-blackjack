package blackjack

import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import blackjack.model.card.Suit
import blackjack.model.player.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러객체 생성`() {
        val cards = mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES))
        val hand = Hand(cards)
        val dealer = Dealer(hand)
        assertThat(dealer.hand).isEqualTo(hand)
    }

    @Test
    fun `딜러의 Hand의 합이 16 이하면 draw`() {
        val cards = mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.FOUR, Suit.SPADES))
        val hand = Hand(cards)
        val dealer = Dealer(hand)

        assertThat(dealer.isRunning()).isTrue
    }
}
