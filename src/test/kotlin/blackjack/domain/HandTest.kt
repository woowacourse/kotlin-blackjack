package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `빈 핸드에 카드를 한 장 추가하면 카드는 총 한 장이다`() {
        val hand = Hand(emptyList())
        hand.addCard(Card(Rank.ACE, Suit.SPADE))
        assertThat(hand.cards.size).isEqualTo(1)
    }
}
