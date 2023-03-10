package blackjack.domain.state

import blackjack.domain.SPADE_ACE
import blackjack.domain.SPADE_JACK
import blackjack.domain.SPADE_KING
import blackjack.domain.SPADE_THREE
import blackjack.domain.SPADE_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StartStateTest {
    @Test
    fun `Start 상태는 카드를 2장 미만으로 보유한다`() {
        assertThrows<IllegalStateException> {
            StartState(SPADE_ACE, SPADE_TWO, SPADE_THREE)
        }
    }

    @Test
    fun `카드를 보유하지 않은 Start 상태의 카드가 주어졌을 때, 추가로 카드를 한 장 뽑으면, Started 상태를 반환한다`() {
        // given
        val cardState = StartState()

        // when
        val expected = cardState.draw(SPADE_ACE)

        // then
        assertThat(expected).isInstanceOf(StartState::class.java)
    }

    @Test
    fun `카드를 한 장 보유 중인 Start 상태가 주어졌을 때, 추가로 뽑은 카드 점수가 21점이면 Blackjack 상태를 반환한다`() {
        // given
        val cardState = StartState(SPADE_JACK)

        // when
        val expected = cardState.draw(SPADE_ACE)

        // then
        assertThat(expected).isInstanceOf(BlackjackState::class.java)
    }

    @Test
    fun `카드를 한 장 보유 중인 Start 상태가 주어졌을 때, 추가로 뽑은 카드 점수가 21점 미만이면 Hit 상태를 반환한다`() {
        // given
        val cardState = StartState(SPADE_JACK)

        // when
        val expected = cardState.draw(SPADE_KING)

        // then
        assertThat(expected).isInstanceOf(HitState::class.java)
    }
}
