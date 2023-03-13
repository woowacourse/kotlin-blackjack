package blackjack.domain.state

import blackjack.domain.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BustTest {

    @Test
    fun `버스트 상태일 때 베팅하면 에러가 발생한다`() {
        val state = Bust(Hand(listOf()), null)

        Assertions.assertThatIllegalStateException().isThrownBy { state.betting(getAnyMoney()) }
            .withMessage("이미 베팅 상태를 지났습니다.")
    }

    @Test
    fun `버스트 상태일 때 드로우하면 에러가 발생한다`() {
        val state = Bust(Hand(listOf()), null)

        Assertions.assertThatIllegalStateException().isThrownBy { state.draw(getAnyCard()) }
            .withMessage("게임이 끝난 상태에서 드로우할 수 없습니다.")
    }

    @Test
    fun `버스트 상태일 때 스테이하면 에러가 발생한다`() {
        val state = Bust(Hand(listOf()), null)

        Assertions.assertThatIllegalStateException().isThrownBy { state.stay() }
            .withMessage("게임이 끝난 상태에서 다른 상태가 될 수 없습니다.")
    }

    @Test
    fun `버스트 상태이고 베팅을 했을 때 수익을 달라고 하면 베팅 금액과 1을 곱한 값을 반환한다`() {
        val state = Bust(Hand(listOf()), Money(10000))

        val actual = state.getProfit()

        Assertions.assertThat(actual).isEqualTo(10000 * 1.0)
    }

    @Test
    fun `버스트 상태이고 베팅을 하지 않았을 때 수익을 달라고 하면 에러가 발생한다`() {
        val state = Bust(Hand(listOf()), null)

        Assertions.assertThatIllegalStateException().isThrownBy { state.getProfit() }
            .withMessage("배팅을 하지 않았습니다.")
    }

    private fun getAnyMoney(): Money = Money(10000)

    private fun getAnyCard(): Card = Card(CardNumber.TEN, CardShape.CLOVER)
}
