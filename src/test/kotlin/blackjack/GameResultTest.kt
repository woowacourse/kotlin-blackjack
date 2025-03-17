package blackjack

import blackjack.domain.GameResult
import blackjack.domain.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `플레이어만 블랙잭이면 플레이어는 1․5배의 수익을 얻는다`() {
        val bettingMoney = Money(10000) // 플레이어가 건 돈
        val result = GameResult.BLACKJACK

        val profit = result.calculatePlayerProfit(bettingMoney)

        assertThat(profit).isEqualTo(15000.0)
    }

    @Test
    fun `플레이어가 이기면 플레이어는 베팅 금액만큼의 수익을 얻는다`() {
        val bettingMoney = Money(10000)
        val result = GameResult.WIN

        val profit = result.calculatePlayerProfit(bettingMoney)

        assertThat(profit).isEqualTo(10000.0)
    }

    @Test
    fun `플레이어와 딜러가 비기면 플레이어는 베팅 금액을 돌려받는다`() {
        val bettingMoney = Money(10000)
        val result = GameResult.DRAW

        val profit = result.calculatePlayerProfit(bettingMoney)

        assertThat(profit).isEqualTo(0.0)
    }

    @Test
    fun `플레이어가 지면 플레이어는 베팅 금액을 잃는다`() {
        val bettingMoney = Money(10000)
        val result = GameResult.LOSE

        val profit = result.calculatePlayerProfit(bettingMoney)

        assertThat(profit).isEqualTo(-10000.0)
    }

    @Test
    fun `딜러가 블랙잭이면 딜러는 플레이어의 베팅 금액만큼의 수익을 얻는다`() {
        val bettingMoney = Money(10000)
        val result = GameResult.BLACKJACK

        val profit = result.calculateDealerProfit(bettingMoney)

        assertThat(profit).isEqualTo(10000.0)
    }
}
