package blackjack.domain.state

import blackjack.domain.SPADE_ACE
import blackjack.domain.SPADE_JACK
import blackjack.domain.SPADE_KING
import blackjack.domain.SPADE_TWO
import blackjack.domain.money.BetMoney
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

    @Test
    fun `Hit 상태이면 수익을 계산할 수 없다`() {
        val cardState = HitState(SPADE_KING, SPADE_JACK)
        assertThrows<IllegalStateException> {
            cardState.profit(other = StayState(SPADE_ACE, SPADE_TWO), BetMoney(1000))
        }
    }

    @Test
    fun `스테이를 반환한다`() {
        val cardState = HitState(SPADE_KING, SPADE_JACK)
        val expected = cardState.stay()

        assertThat(expected).isInstanceOf(StayState::class.java)
    }

    @Test
    fun `첫 번째 카드를 반환한다`() {
        val cardState = HitState(SPADE_KING, SPADE_JACK)
        val expected = cardState.getFirstCard()

        assertThat(expected).isEqualTo(SPADE_KING)
    }

    @Test
    fun `게임이 종료되지 않은 상태에서 수익 계산을 하려고 하면 예외가 발생한다`() {
        val cardState = HitState(SPADE_KING, SPADE_JACK)

        assertThrows<IllegalStateException> {
            cardState.profit(StayState(), betMoney = BetMoney(1000))
        }
    }
}
