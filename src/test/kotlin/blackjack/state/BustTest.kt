package blackjack.state

import blackjack.fixture.createBustState
import blackjack.model.Betting
import blackjack.model.Profit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BustTest {
    @ParameterizedTest
    @ValueSource(ints = [20, 21, 22])
    fun `Bust 상태면 betting 금액만큼 손해다`(otherPoint: Int) {
        // given
        val state = createBustState()
        val betting = Betting(10_000)
        val expect = Profit(-10_000)
        // when
        val actual = state.calculateProfit(betting, otherPoint)
        // then
        assertThat(actual).isEqualTo(expect)
    }
}
