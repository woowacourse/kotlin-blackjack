package blackjack.model

class RewardStatistics(
    dealer: Dealer,
    playerStatistics: PlayerStatistics,
    rewardPayout: RewardPayout = RewardPayout(),
) {
    val dealerRewardStatistic =
        RewardStatistic(dealer, -playerStatistics.map { rewardPayout.reward(it) }.reduce { a, b -> a + b })
    val playerRewardStatistics = playerStatistics.map {
        RewardStatistic(it.player, rewardPayout.reward(it))
    }
}
