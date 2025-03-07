package blackjack.domain.model

class WinLossStatistics {
    fun calculatePlayerWinLoss(
        dealerCards: List<Card>,
        playerCards: List<Card>,
    ): WinLoss {
        val dealerScore = Rule.calculateResultByCards(dealerCards)
        val playerScore = Rule.calculateResultByCards(playerCards)

        if (dealerScore == playerScore) {
            return WinLoss.DRAW
        }
        if (dealerScore > playerScore) {
            return WinLoss.LOSE
        }
        return WinLoss.WIN
    }
}
