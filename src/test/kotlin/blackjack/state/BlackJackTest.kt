package blackjack.state

import blackjack.fixture.BETTING_10000
import blackjack.fixture.BLACKJACK_POINT
import blackjack.fixture.PROFIT_15000
import blackjack.fixture.PROFIT_ZERO
import blackjack.fixture.createBlackjackState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BlackJackTest {
    @Test
    fun `BlackJack 상태 - 상대방과 ratePoint 와 같으면 이득은 0이다`() {
        // given
        val state = createBlackjackState()
        val otherPoint = BLACKJACK_POINT
        val betting = BETTING_10000
        val expect = PROFIT_ZERO
        // when
        val actual = state.calculateProfit(betting, otherPoint)
        // then
        assertThat(actual).isEqualTo(expect)
    }

    @ParameterizedTest
    @ValueSource(ints = [20, 21])
    @DisplayName("BlackJack 상태 - 상대방보다 ratePoint 가 높으면 betting 금액의 1.5배만큼 이득이다")
    fun `BlackJackTest`(otherPoint: Int) {
        // given
        val state = createBlackjackState()
        val betting = BETTING_10000
        val expect = PROFIT_15000
        // when
        val actual = state.calculateProfit(betting, otherPoint)
        // then
        assertThat(actual).isEqualTo(expect)
    }
}
