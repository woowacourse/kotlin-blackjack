package blackjack.domain.model.progress
import blackjack.domain.model.participant.CardStatus
import blackjack.domain.model.participant.Player
import java.lang.IllegalArgumentException

class ProfitStatistics(
    private val betHistory: BetHistory,
    private val winLossStatistics: WinLossStatistics,
) {
    val playerProfits: Map<Player, Double>
        get() {
            val playerWinLoseInfo = winLossStatistics.playerWinLoseInfo
            return betHistory.playerBets.entries.associate { (player, betAmount) ->
                val winLoss =
                    playerWinLoseInfo[player] ?: throw IllegalArgumentException(ERROR_NOT_FOUND_WIN_LOSS.format(player))
                val profitRate = calculateProfitRate(winLoss, player.cardStatus)

                player to calculateProfit(betAmount, profitRate)
            }
        }
    val dealerProfits: Double
        get() = -playerProfits.values.sum() + 0.0 // -0 방지

    private fun calculateProfit(
        betAmount: Int,
        profitRate: Double,
    ): Double = betAmount * profitRate

    private fun calculateProfitRate(
        winLoss: WinLoss,
        cardStatus: CardStatus,
    ): Double =
        when (winLoss) {
            WinLoss.WIN -> if (cardStatus == CardStatus.BLACKJACK) 1.5 else 1.0
            WinLoss.LOSE -> -1.0
            WinLoss.DRAW -> 0.0
        }

    companion object {
        private const val ERROR_NOT_FOUND_WIN_LOSS = "%s의 승패 정보가 없어 수익률을 계산할 수 없습니다"
    }
}
