package blackjack.domain.model

class WinLossStatistics {
    private val dealerResults = mutableMapOf(WinLoss.WIN to 0, WinLoss.LOSE to 0, WinLoss.DRAW to 0)

    fun calculatePlayerWinLoss(
        dealerCards: List<Card>,
        playerCards: List<Card>,
    ): WinLoss {
        val dealerScore = Rule.calculateResultByCards(dealerCards)
        val playerScore = Rule.calculateResultByCards(playerCards)

        if (dealerScore == playerScore) {
            dealerResults[WinLoss.DRAW] = (dealerResults[WinLoss.DRAW] ?: 0) + 1
            return WinLoss.DRAW
        }
        if (dealerScore > playerScore) {
            dealerResults[WinLoss.WIN] = (dealerResults[WinLoss.WIN] ?: 0) + 1
            return WinLoss.LOSE
        }
        dealerResults[WinLoss.LOSE] = (dealerResults[WinLoss.LOSE] ?: 0) + 1
        return WinLoss.WIN
    }

    fun getDealerWinLossText(): String {
        val dealerWinText =
            if (dealerResults[WinLoss.WIN] != 0) {
                dealerResults[WinLoss.WIN].toString() + "승 "
            } else {
                ""
            }
        val dealerDrawText =
            if (dealerResults[WinLoss.DRAW] != 0) {
                dealerResults[WinLoss.DRAW].toString() + "무 "
            } else {
                ""
            }
        val dealerLossText =
            if (dealerResults[WinLoss.LOSE] != 0) {
                dealerResults[WinLoss.LOSE].toString() + "패 "
            } else {
                ""
            }

        return (dealerWinText + dealerDrawText + dealerLossText).trim()
    }
}
