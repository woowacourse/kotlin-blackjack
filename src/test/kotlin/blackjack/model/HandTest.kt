package blackjack.model

import blackjack.model.card.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun Hand(cards: List<Card>): Hand {
    return Hand(cards.toMutableList())
}

private const val BURST_CONDITION = 21

class HandTest {
    @Test
    fun `카드에 ACE가 없는 경우 점수를 구한다`() {
        // given
        val hand = Hand(listOf(SPADE_FIVE, HEART_THREE))

        // when
        val actual = hand.score(BURST_CONDITION)

        // then
        assertThat(actual).isEqualTo(8)
    }

    @Test
    fun `ACE를 11로 계산할 경우 21이 넘지 않는다면, 11로 계산한다`() {
        // given
        val hand = Hand(listOf(SPADE_ACE, HEART_KING))

        // when
        val actual = hand.score(BURST_CONDITION)

        // then
        assertThat(actual).isEqualTo(21)
    }

    @Test
    fun `ACE를 11로 계산할 경우 21이 넘는다면, 1로 계산한다`() {
        // given
        val hand = Hand(listOf(SPADE_TEN, HEART_SEVEN, SPADE_ACE))

        // when
        val actual = hand.score(BURST_CONDITION)

        // then
        assertThat(actual).isEqualTo(18)
    }
}
