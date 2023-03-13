package blackjack.domain.state

import blackjack.domain.DIAMOND_ACE
import blackjack.domain.DIAMOND_JACK
import blackjack.domain.SPADE_ACE
import blackjack.domain.SPADE_JACK
import blackjack.domain.SPADE_TWO
import blackjack.domain.money.BetMoney
import blackjack.domain.money.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackjackStateTest {
    private lateinit var blackjackState: CardState

    @BeforeEach
    fun setUp() {
        blackjackState = BlackjackState(SPADE_ACE, SPADE_JACK)
    }

    @Test
    fun `상대방도 블랙잭일 때 수익이 없다`() {
        val other = BlackjackState(DIAMOND_ACE, DIAMOND_JACK)
        val expected = blackjackState.profit(other, BetMoney(1000))

        assertThat(expected).isEqualTo(Money(0))
    }

    @Test
    fun `상대방이 블랙잭이 아닐 때 베팅 금액의 절반을 합한 값을 반환한다`() {
        val other = StayState(DIAMOND_ACE, SPADE_TWO)
        val expected = blackjackState.profit(other, BetMoney(1000))

        assertThat(expected).isEqualTo(Money(1500))
    }
}
