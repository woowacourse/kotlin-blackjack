package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `패의 점수를 계산할 때 에이스를 1로 처리한다`() {
        val hand = Hand(listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING), Card(Suit.SPADE, Rank.KING)))
        assertThat(hand.computePoint()).isEqualTo(21)
    }

    @Test
    fun `패의 점수를 계산할 때 에이스를 11로 처리한다`() {
        val hand = Hand(listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING)))
        assertThat(hand.computePoint()).isEqualTo(21)
    }
}
