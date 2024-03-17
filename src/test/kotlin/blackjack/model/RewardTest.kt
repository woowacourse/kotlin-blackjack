package blackjack.model

import blackjack.model.statistics.PlayerStatistic
import blackjack.model.statistics.PlayerStatistics
import blackjack.model.statistics.RewardStatistics
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RewardTest {
    @Test
    fun `플레이어가 딜러에게 이겼을 때 블랙잭일 경우 블랙잭 배당률만큼의 수익을 얻는다`() {
        val player = buildPlayer("fiona", 1000.0, ace, ten)
        val playerStatistic = PlayerStatistic(player, GameResult.Win)
        val actual = RewardPayout().reward(playerStatistic)
        assertThat(actual).isEqualTo(Money(1500.0))
    }

    @Test
    fun `플레이어가 딜러에게 이겼을 때 블랙잭이 아닐 경우 플레이어는 건 돈만큼 수익을 얻는다`() {
        val player = buildPlayer("fiona", 1000.0, four, five)
        val playerStatistic = PlayerStatistic(player, GameResult.Win)
        val actual = RewardPayout().reward(playerStatistic)
        assertThat(actual).isEqualTo(Money(1000.0))
    }

    @Test
    fun `플레이어와 딜러가 비겼을때 플레이어는 수익을 얻지 못한다`() {
        val player = buildPlayer("fiona", 1000.0)
        val playerStatistic = PlayerStatistic(player, GameResult.Draw)
        val actual = RewardPayout().reward(playerStatistic)
        assertThat(actual).isEqualTo(Money(0.0))
    }

    @Test
    fun `플레이어와 딜러가 비겼을때 플레이어는 건 돈을 잃는다`() {
        val player = buildPlayer("fiona", 1000.0)
        val playerStatistic = PlayerStatistic(player, GameResult.Lose)
        val actual = RewardPayout().reward(playerStatistic)
        assertThat(actual).isEqualTo(Money(-1000.0))
    }

    @Test
    fun `딜러의 수익은 플레이어 수익 손실 합산의 음수배이다`() {
        val player1 = buildPlayer("pamyo", 1000.0, two, two)
        val player2 = buildPlayer("cat", 1000.0, two, two)
        val dealer = buildDealer(five, five)
        val playerStatistics = PlayerStatistics(dealer, listOf(player1, player2))
        val actual = RewardStatistics(dealer, playerStatistics).dealerRewardStatistic.reward
        assertThat(actual).isEqualTo(Money(2000.0))
    }
}
