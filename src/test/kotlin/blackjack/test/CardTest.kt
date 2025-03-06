package blackjack.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Card(
    val rank: Rank,
    val suit: Suit,
)

class CardTest {
    @Test
    fun `카드는 랭크와 수트로 구성된다`() {
        val card = Card(rank = Ace(), suit = Suit.SPADE)
        assertThat(card.rank is Rank).isTrue()
        assertThat(card.suit is Suit).isTrue()
    }
}
