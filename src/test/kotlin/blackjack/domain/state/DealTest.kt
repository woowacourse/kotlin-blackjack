package blackjack.domain.state

import blackjack.domain.*
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealTest {

    @Test
    fun `딜 상태일 때 스테이하면 에러가 발생한다`() {
        val state = Deal(Hand(listOf()), null)

        Assertions.assertThatIllegalStateException().isThrownBy { state.stay() }
            .withMessage("게임이 진행중일 때만 스테이 상태가 될 수 있습니다.")
    }

    @Test
    fun `딜 상태일 때 수익을 달라고 하면 에러가 발생한다`() {
        val state = Deal(Hand(listOf()), null)

        Assertions.assertThatIllegalStateException().isThrownBy { state.getProfit() }
            .withMessage("게임이 끝나기 전까지는 수익을 계산할 수 없습니다.")
    }

    @Test
    fun `딜 상태일 때 베팅하면 에러가 발생한다`() {
        val state = Deal(Hand(listOf()), null)

        Assertions.assertThatIllegalStateException().isThrownBy { state.betting(getAnyMoney()) }
            .withMessage("이미 베팅을 했습니다.")
    }

    @Test
    fun `딜 상태이고 드로우 했을 때 핸드의 카드 개수가 2장 미만이면 딜 상태가 된다`() {
        var state: State = Deal(Hand(listOf()), null)

        state = state.draw(getAnyCard())

        assertThat(state).isInstanceOf(Deal::class.java)
    }

    @Test
    fun `딜 상태이고 드로우 했을 때 핸드의 카드 개수가 2장 이상이면 히트 상태가 된다`() {
        var state: State = Deal(Hand(listOf()), null)
        state = state.draw(getAnyCard())

        state = state.draw(getAnyCard())

        assertThat(state).isInstanceOf(Hit::class.java)
    }

    private fun getAnyMoney(): Money = Money(10000)

    private fun getAnyCard(): Card = Card(CardNumber.ACE, CardShape.CLOVER)
}
