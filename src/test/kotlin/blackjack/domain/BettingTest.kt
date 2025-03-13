package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BettingTest {
    @Test
    fun `블랙잭이 되어 이길 경우 베팅 금액의 1,5 배를 받는다`() {
        assertThat(Betting(10000).toProfit(ParticipantState.WIN, Score.Blackjack)).isEqualTo(15000)
    }

    @Test
    fun `승리한 플레이어는 베팅한 만큼의 금액을 받는다`() {
        assertThat(Betting(10000).toProfit(ParticipantState.WIN, Score.Hittable(18))).isEqualTo(10000)
    }

    @Test
    fun `비길 경우 플레이어는 베팅한 금액을 돌려받는다`() {
        assertThat(Betting(10000).toProfit(ParticipantState.DRAW, Score.Blackjack)).isEqualTo(0)
    }

    @Test
    fun `질 경우 배팅 금액을 모두 잃는다`() {
        assertThat(Betting(10000).toProfit(ParticipantState.LOSE, Score.Bust(25))).isEqualTo(-10000)
    }
}
