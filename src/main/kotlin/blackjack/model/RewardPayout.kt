package blackjack.model

import blackjack.model.statistics.PlayerStatistic

class RewardPayout(private val rewardRateRule: RewardRateRule = BlackjackStandardRewardRateRule()) {
    fun reward(playerStatistic: PlayerStatistic): Money = playerStatistic.player.stake * rewardRateRule.rate(playerStatistic)
}
