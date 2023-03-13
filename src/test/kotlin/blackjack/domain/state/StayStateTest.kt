package blackjack.domain.state

import blackjack.domain.DIAMOND_JACK
import blackjack.domain.DIAMOND_KING
import blackjack.domain.SPADE_ACE
import blackjack.domain.SPADE_EIGHT
import blackjack.domain.SPADE_JACK
import blackjack.domain.SPADE_KING
import blackjack.domain.SPADE_NINE
import blackjack.domain.SPADE_TWO
import blackjack.domain.money.BetMoney
import blackjack.domain.money.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StayStateTest {
    private lateinit var stayState: CardState

    @BeforeEach
    fun setUp() {
        stayState = StayState(SPADE_KING, SPADE_NINE)
    }

    @Test
    fun `상대방이 버스트이면 투자 금액만큼 수익을 얻는다`() {
        val other = BustState(SPADE_KING, SPADE_JACK, SPADE_TWO)
        val expected = stayState.profit(other, BetMoney(1000))

        assertThat(expected).isEqualTo(Money(1000))
    }

    @Test
    fun `상대방이 블랙잭이면 투자 금액을 잃는다`() {
        val other = BlackjackState(SPADE_KING, SPADE_ACE)
        val expected = stayState.profit(other, BetMoney(1000))

        assertThat(expected).isEqualTo(-Money(1000))
    }

    @Test
    fun `상대방이 스테이일 때, 나의 점수가 높으면 투자 금액만큼 수익을 얻는다`() {
        val me = StayState(SPADE_KING, SPADE_NINE)
        val other = StayState(SPADE_KING, SPADE_EIGHT)
        val expected = me.profit(other, BetMoney(1000))

        assertThat(expected).isEqualTo(Money(1000))
    }

    @Test
    fun `상대방이 스테이일 때, 상대방 점수가 높으면 투자 금액을 잃는다`() {
        val me = StayState(SPADE_KING, SPADE_NINE)
        val other = StayState(DIAMOND_KING, DIAMOND_JACK)
        val expected = me.profit(other, BetMoney(1000))

        assertThat(expected).isEqualTo(-Money(1000))
    }

    @Test
    fun `상대방이 스테이일 때, 상대방과 점수가 같으면 수익이 없다`() {
        val me = StayState(SPADE_KING, SPADE_JACK)
        val other = StayState(DIAMOND_KING, DIAMOND_JACK)
        val expected = me.profit(other, BetMoney(1000))

        assertThat(expected).isEqualTo(Money(0))
    }
}
