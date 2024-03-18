package blackjack.state

import blackjack.fixture.BETTING_10000
import blackjack.fixture.PROFIT_10000
import blackjack.fixture.createBustState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BustTest {
    @ParameterizedTest
    @ValueSource(ints = [20, 21, 22])
    fun `Bust 상태면 betting 금액만큼 손해다`(otherPoint: Int) {
        // given
        val state = createBustState()
        val betting = BETTING_10000
        val expect = -PROFIT_10000
        // when
        val actual = state.calculateProfit(betting, otherPoint)
        // then
        assertThat(actual).isEqualTo(expect)
    }
}
