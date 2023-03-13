package blackjack.domain.state

import blackjack.domain.*
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Hand
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class BustTest {

    @Test
    fun `버스트 상태일 때 베팅하면 에러가 발생한다`() {
        val state = Bust(Hand(listOf()), null)

        assertThatIllegalStateException().isThrownBy { state.betting(getAnyMoney()) }
            .withMessage("이미 베팅 상태를 지났습니다.")
    }

    @Test
    fun `버스트 상태일 때 드로우하면 에러가 발생한다`() {
        val state = Bust(Hand(listOf()), null)

        assertThatIllegalStateException().isThrownBy { state.draw(getAnyCard()) }
            .withMessage("게임이 끝난 상태에서 드로우할 수 없습니다.")
    }

    @Test
    fun `버스트 상태일 때 스테이하면 에러가 발생한다`() {
        val state = Bust(Hand(listOf()), null)

        assertThatIllegalStateException().isThrownBy { state.stay() }
            .withMessage("게임이 끝난 상태에서 다른 상태가 될 수 없습니다.")
    }

    @Test
    fun `버스트 상태이고 베팅을 했을 때 수익을 달라고 하면 베팅 금액과 1을 곱한 값을 반환한다`() {
        val betAmount = 10000
        val state = Bust(Hand(listOf()), Money(betAmount))

        val actual = state.getProfit()

        assertThat(actual).isEqualTo(betAmount * 1.0)
    }

    @Test
    fun `버스트 상태이고 베팅을 하지 않았을 때 수익을 달라고 하면 에러가 발생한다`() {
        val state = Bust(Hand(listOf()), null)

        assertThatIllegalStateException().isThrownBy { state.getProfit() }
            .withMessage("배팅을 하지 않았습니다.")
    }

    private fun getAnyMoney(): Money = Money(10000)

    private fun getAnyCard(): Card = Card(CardNumber.TEN, CardShape.CLOVER)
}
