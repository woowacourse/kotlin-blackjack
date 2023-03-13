package blackjack.domain.state

import blackjack.domain.SPADE_JACK
import blackjack.domain.SPADE_KING
import blackjack.domain.SPADE_TWO
import blackjack.domain.money.BetMoney
import blackjack.domain.money.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BustStateTest {
    @Test
    fun `버스트 상태는 21점을 초과해야 한다`() {
        assertDoesNotThrow {
            BustState(SPADE_KING, SPADE_JACK, SPADE_TWO)
        }
    }

    @Test
    fun `버스트 상태는 21점을 초과하지 않으면 예외가 발생한다`() {
        assertThrows<IllegalStateException> {
            BustState(SPADE_KING, SPADE_JACK)
        }
    }

    @Test
    fun `버스트 상태는 수익 계산시 배팅 금액을 잃는다`() {
        val cardState = BustState(SPADE_KING, SPADE_JACK, SPADE_TWO)

        val expected = cardState.profit(StayState(SPADE_KING, SPADE_JACK, SPADE_TWO), betMoney = BetMoney(1000))

        assertThat(expected).isEqualTo(Money(-1000))
    }
}
