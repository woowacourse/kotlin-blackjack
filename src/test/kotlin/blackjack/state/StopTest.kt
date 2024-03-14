package blackjack.state

import blackjack.fixture.createCard
import blackjack.fixture.createStopState
import blackjack.model.Betting
import blackjack.model.Profit
import blackjack.model.card.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StopTest {
    @Test
    fun `Stop 상태 - 상대방보다 ratePoint 가 낮으면 betting 금액만큼 손해다`() {
        // given
        val state =
            createStopState(
                createCard(rank = Rank.TEN),
                createCard(rank = Rank.TEN),
            )
        val otherPoint = 21
        val betting = Betting(10_000)
        val expect = Profit(-10_000)
        // when
        val actual = state.calculateProfit(betting, otherPoint)
        // then
        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `Stop 상태 - 상대방보다 ratePoint 가 높으면 betting 금액만큼 이득이다`() {
        // given
        val state =
            createStopState(
                createCard(rank = Rank.TEN),
                createCard(rank = Rank.TEN),
            )
        val otherPoint = 19
        val betting = Betting(10_000)
        val expect = Profit(10_000)
        // when
        val actual = state.calculateProfit(betting, otherPoint)
        // then
        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `Stop 상태 - 상대방보다 ratePoint 와 같으면 이득은 0이다`() {
        // given
        val state =
            createStopState(
                createCard(rank = Rank.TEN),
                createCard(rank = Rank.TEN),
            )
        val otherPoint = 20
        val betting = Betting(10_000)
        val expect = Profit(0)
        // when
        val actual = state.calculateProfit(betting, otherPoint)
        // then
        assertThat(actual).isEqualTo(expect)
    }
}
