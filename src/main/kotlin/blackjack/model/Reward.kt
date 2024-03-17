package blackjack.model

class Reward(private val rewardRateRule: RewardRateRule = BlackjackStandardRewardRateRule()) {
    fun moneyFrom(playerStatistic: PlayerStatistic): Money =
        playerStatistic.player.stake * rewardRateRule.rate(playerStatistic)
}
