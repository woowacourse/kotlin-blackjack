package blackjack.model

import model.cards.Card
import model.cards.Hand
import model.cards.Rank
import model.cards.Suit
import model.participants.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드의 합이 16을 초과하지 않으면 hit 한다`() {
        val dealer = dealer(Rank.JACK, Rank.SIX)
        assertThat(dealer.isHit()).isTrue
    }

    @Test
    fun `카드의 합이 16을 초과하면 stay 한다`() {
        val dealer = dealer(Rank.JACK, Rank.SEVEN)
        assertThat(dealer.isHit()).isFalse
    }

    private fun dealer(vararg ranks: Rank): Dealer = Dealer(
        Hand(ranks.map { Card(it, Suit.DIAMOND) })
    )
}
