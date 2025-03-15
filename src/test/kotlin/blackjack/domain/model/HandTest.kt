package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand
import blackjack.domain.model.card.Rank
import blackjack.domain.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `패의 점수를 계산할 때 에이스를 1로 처리한다`() {
        val hand = Hand(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING), Card(Suit.SPADE, Rank.KING))
        assertThat(hand.computePoint()).isEqualTo(21)
    }

    @Test
    fun `패의 점수를 계산할 때 에이스를 11로 처리한다`() {
        val hand = Hand(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING))
        assertThat(hand.computePoint()).isEqualTo(21)
    }

    @Test
    fun `패의 카드를 반환한다`() {
        val hand = Hand(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.TWO))
        assertThat(hand.show()).isEqualTo(listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.TWO)))
    }

    @Test
    fun `패에 카드를 추가한다`() {
        val hand = Hand(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.TWO))
        hand.add(listOf(Card(Suit.HEART, Rank.KING)))
        assertThat(hand.show()).isEqualTo(listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.TWO), Card(Suit.HEART, Rank.KING)))
    }
}
