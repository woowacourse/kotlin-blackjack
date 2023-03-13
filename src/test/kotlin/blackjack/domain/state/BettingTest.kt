package blackjack.domain.state

import blackjack.domain.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class BettingTest {

    @Test
    fun `베팅 상태일 때 스테이하면 에러가 발생한다`() {
        val state = Betting(Hand(listOf()))

        assertThatIllegalStateException().isThrownBy { state.stay() }
            .withMessage("게임이 진행중일 때만 스테이 상태가 될 수 있습니다.")
    }

    @Test
    fun `베팅 상태일 때 수익을 달라고 하면 에러가 발생한다`() {
        val state = Betting(Hand(listOf()))

        assertThatIllegalStateException().isThrownBy { state.getProfit() }
            .withMessage("게임이 끝나기 전까지는 수익을 계산할 수 없습니다.")
    }

    @Test
    fun `베팅 상태일 때 드로우하면 에러가 발생한다`() {
        val state = Betting(Hand(listOf()))

        assertThatIllegalStateException().isThrownBy { state.draw(getAnyCard()) }
            .withMessage("베팅부터 해야합니다.")
    }

    @Test
    fun `베팅 상태일 때 베팅하면 딜 상태로 넘어간다`() {
        var state: State = Betting(Hand(listOf()))

        state = state.betting(Money(10000))

        assertThat(state).isInstanceOf(Deal::class.java)
    }

    private fun getAnyCard(): Card = Card(CardNumber.ACE, CardShape.CLOVER)
}
