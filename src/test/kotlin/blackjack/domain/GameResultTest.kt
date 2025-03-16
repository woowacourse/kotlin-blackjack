package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `블랙잭이 되어 이길 경우 베팅 금액의 1,5 배를 받는다`() {
        val bettingAmount = 1_000.0
        assertThat(GameResult.BLACKJACK_WIN.calculateProfit(bettingAmount)).isEqualTo(1_500.0)
    }

    @Test
    fun `승리한 플레이어는 베팅한 만큼의 금액을 받는다`() {
        val bettingAmount = 1_000.0
        assertThat(GameResult.WIN.calculateProfit(bettingAmount)).isEqualTo(1_000.0)
    }

    @Test
    fun `비길 경우 플레이어는 베팅한 금액을 돌려받는다`() {
        val bettingAmount = 1_000.0
        assertThat(GameResult.DRAW.calculateProfit(bettingAmount)).isEqualTo(0.0)
    }

    @Test
    fun `질 경우 배팅 금액을 모두 잃는다`() {
        val bettingAmount = 1_000.0
        assertThat(GameResult.LOSE.calculateProfit(bettingAmount)).isEqualTo(-1_000.0)
    }
}
