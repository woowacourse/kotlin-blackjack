package blackjack.domain.state

import blackjack.domain.SPADE_ACE
import blackjack.domain.SPADE_JACK
import blackjack.domain.SPADE_KING
import blackjack.domain.SPADE_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HitStateTest {
    @Test
    fun `카드를 2장 이상 보유하며 추가로 뽑을 수 있는 상태이다`() {
        assertThrows<IllegalStateException> {
            HitState(SPADE_TWO)
        }
    }

    @Test
    fun `카드를 한 장 뽑았을 때, 카드 점수가 21점 초과이면 Bust 상태를 반환한다`() {
        // given
        val cardState = HitState(SPADE_KING, SPADE_JACK)

        // when
        val expected = cardState.draw(SPADE_TWO)

        // then
        assertThat(expected).isInstanceOf(BustState::class.java)
    }

    @Test
    fun `카드를 한 장 뽑았을 때, 카드 점수가 21점 이하이면 Hit 상태를 반환한다`() {
        // given
        val cardState = HitState(SPADE_KING, SPADE_JACK)

        // when
        val expected = cardState.draw(SPADE_ACE)

        // then
        assertThat(expected).isInstanceOf(HitState::class.java)
    }
}
