package blackjack.domain

import blackjack.enums.Rank
import blackjack.enums.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱에 카드 한 장이 남아있다면 해당 카드가 나온다`() {
        val deck = Deck(listOf(Card(Rank.ACE, Suit.SPADE)))
        val card = deck.pick()
        assertThat(card).isEqualTo(Card(Rank.ACE, Suit.SPADE))
    }

    @Test
    fun `덱에서 카드 한 장을 뽑으면 마지막 카드가 나온다`() {
        val deck = Deck(listOf(Card(Rank.THREE, Suit.SPADE), Card(Rank.ACE, Suit.SPADE)))
        val card = deck.pick()
        assertThat(card).isEqualTo(Card(Rank.ACE, Suit.SPADE))
    }
}
