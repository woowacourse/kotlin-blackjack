package blackjack

import Card
import Cards
import Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드의 합이 16을 초과하지 않으면 hit 한다`() {
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(Rank.JACK, Suit.DIAMOND),
                    Card(Rank.SIX, Suit.CLOVER),
                ),
            ),
        )
        assertThat(dealer.isHit()).isTrue
    }

    @Test
    fun `카드의 합이 16을 초과하면 stay 한다`() {
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(Rank.JACK, Suit.DIAMOND),
                    Card(Rank.SEVEN, Suit.CLOVER),
                ),
            ),
        )
        assertThat(dealer.isHit()).isFalse
    }
}
