package blackjack.model

fun interface RewardRateRule {
    fun rate(playerStatistic: PlayerStatistic): Double
}

class BlackjackStandardRewardRateRule : RewardRateRule {
    override fun rate(playerStatistic: PlayerStatistic): Double =
        with(playerStatistic) {
            when {
                gameResult == GameResult.Win && player.isBlackjack() -> BLACKJACK_REWARD_RATE
                gameResult == GameResult.Win -> WIN_REWARD_RATE
                gameResult == GameResult.Draw -> DRAW_REWARD_RATE
                else -> LOSE_REWARD_RATE
            }
        }

    companion object {
        private const val BLACKJACK_REWARD_RATE = 1.5
        private const val WIN_REWARD_RATE = 1.0
        private const val DRAW_REWARD_RATE = 0.0
        private const val LOSE_REWARD_RATE = -1.0
    }
}
