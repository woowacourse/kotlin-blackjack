package blackjack.domain.model

import blackjack.Fixtures.HEART_ACE
import blackjack.Fixtures.HEART_KING
import blackjack.Fixtures.HEART_QUEEN
import blackjack.Fixtures.HEART_TWO
import blackjack.domain.model.card.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `패의 점수를 계산할 때 에이스를 1로 처리한다`() {
        val hand = Hand(HEART_ACE, HEART_QUEEN, HEART_KING)
        assertThat(hand.point()).isEqualTo(21)
    }

    @Test
    fun `패의 점수를 계산할 때 에이스를 11로 처리한다`() {
        val hand = Hand(HEART_ACE, HEART_KING)
        assertThat(hand.point()).isEqualTo(21)
    }

    @Test
    fun `패의 카드를 반환한다`() {
        val hand = Hand(HEART_ACE, HEART_TWO)
        assertThat(hand.open()).isEqualTo(listOf(HEART_ACE, HEART_TWO))
    }

    @Test
    fun `패에 카드를 추가한다`() {
        val hand = Hand(HEART_ACE, HEART_TWO)
        hand.add(listOf(HEART_KING))
        assertThat(hand.open()).isEqualTo(listOf(HEART_ACE, HEART_TWO, HEART_KING))
    }
}
