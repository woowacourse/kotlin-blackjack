package blackjack.domain.model.progress

import blackjack.domain.model.participant.CardStatus
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player

class WinLossStatistics {
    private val dealerResults = mutableMapOf<WinLoss, Int>()

    fun loadDealerResults() = dealerResults.toMap()

    fun calculatePlayerWinLoss(
        dealer: Dealer,
        player: Player,
    ): WinLoss {
        val dealerBestValue = dealer.handCards.calculateBestCardValue()
        val playerBestValue = player.handCards.calculateBestCardValue()
        val dealerCardStatus = dealer.handCards.getStatus()
        val playerCardStatus = player.handCards.getStatus()

        val playerResult =
            when {
                playerCardStatus == CardStatus.BUST -> WinLoss.LOSE
                (dealerCardStatus != CardStatus.BUST) && (dealerBestValue > playerBestValue) -> WinLoss.LOSE
                (dealerCardStatus == CardStatus.BLACKJACK) && (playerCardStatus != CardStatus.BLACKJACK) -> WinLoss.LOSE
                (dealerBestValue == playerBestValue) -> WinLoss.DRAW
                else -> WinLoss.WIN
            }
        updateDealerResult(playerResult)
        return playerResult
    }

    private fun updateDealerResult(playerResult: WinLoss) {
        val dealerResult =
            when (playerResult) {
                WinLoss.WIN -> WinLoss.LOSE
                WinLoss.LOSE -> WinLoss.WIN
                WinLoss.DRAW -> WinLoss.DRAW
            }
        dealerResults[dealerResult] = (dealerResults[dealerResult] ?: 0) + 1
    }
}
