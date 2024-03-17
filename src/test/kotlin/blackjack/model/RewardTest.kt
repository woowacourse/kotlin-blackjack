package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RewardTest {
    @Test
    fun `플레이어가 딜러에게 이겼을 때 블랙잭일 경우 블랙잭 배당률만큼의 수익을 얻는다`() {
        val player = buildPlayer("fiona", 1000.0, ace, ten)
        val playerStatistic = PlayerStatistic(player, GameResult.Win)
        val actual = Reward(playerStatistic).reward
        assertThat(actual).isEqualTo(1500.0)
    }

    @Test
    fun `플레이어가 딜러에게 이겼을 때 블랙잭이 아닐 경우 플레이어는 건 돈만큼 수익을 얻는다`() {
        val player = buildPlayer("fiona", 1000.0, four, five)
        val playerStatistic = PlayerStatistic(player, GameResult.Win)
        val actual = Reward(playerStatistic).reward
        assertThat(actual).isEqualTo(1000.0)
    }

    @Test
    fun `플레이어와 딜러가 비겼을때 플레이어는 수익을 얻지 못한다`() {
        val player = buildPlayer("fiona", 1000.0)
        val playerStatistic = PlayerStatistic(player, GameResult.Draw)
        val actual = Reward(playerStatistic).reward
        assertThat(actual).isEqualTo(0.0)
    }

    @Test
    fun `플레이어와 딜러가 비겼을때 플레이어는 건 돈을 잃는다`() {
        val player = buildPlayer("fiona", 1000.0)
        val playerStatistic = PlayerStatistic(player, GameResult.Lose)
        val actual = Reward(playerStatistic).reward
        assertThat(actual).isEqualTo(-1000.0)
    }
}
