package blackjack.domain.state

import blackjack.domain.*
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Hand
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class HitTest {

    @Test
    fun `히트 상태일 때 베팅하면 에러가 발생한다`() {
        val state = Hit(Hand(listOf()), null)

        assertThatIllegalStateException().isThrownBy { state.betting(getAnyMoney()) }
            .withMessage("이미 베팅 상태를 지났습니다.")
    }

    @Test
    fun `히트 상태일 때 스테이하면 스테이 상태를 반환한다`() {
        val state = Hit(Hand(listOf()), null)

        val actual = state.stay()

        assertThat(actual).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `히트 상태일 때 수익을 달라고 하면 에러가 발생한다`() {
        val state = Hit(Hand(listOf()), null)

        assertThatIllegalStateException().isThrownBy { state.getProfit() }
            .withMessage("게임이 끝나기 전까지는 수익을 계산할 수 없습니다.")
    }

    @Test
    fun `히트 상태이고 드로우했을 때 핸드가 블랙잭이면 블랙잭 상태를 반환한다`() {
        val state = Hit(Hand(listOf(Card(CardNumber.TEN, CardShape.CLOVER))), null)

        val actual = state.draw(Card(CardNumber.ACE, CardShape.HEART))

        assertThat(actual).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun `히트 상태이고 드로우했을 때 핸드가 버스트이면 버스트 상태를 반환한다`() {
        val state =
            Hit(Hand(listOf(Card(CardNumber.TEN, CardShape.CLOVER), Card(CardNumber.TEN, CardShape.CLOVER))), null)

        val actual = state.draw(Card(CardNumber.TEN, CardShape.HEART))

        assertThat(actual).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `히트 상태이고 드로우했을 때 핸드가 블랙잭도 아니고 버스트도 아니면 히트 상태를 반환한다`() {
        val state = Hit(Hand(listOf(Card(CardNumber.TEN, CardShape.CLOVER))), null)

        val actual = state.draw(Card(CardNumber.TEN, CardShape.HEART))

        assertThat(actual).isInstanceOf(Hit::class.java)
    }

    private fun getAnyMoney(): Money = Money(10000)
}
