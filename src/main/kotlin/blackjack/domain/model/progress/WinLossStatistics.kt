package blackjack.domain.model.progress

import blackjack.domain.model.card.Card
import blackjack.domain.model.participant.CardStatus
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player

class WinLossStatistics {
    private val dealerResults = mutableMapOf<WinLoss, Int>()

    fun loadDealerResults() = dealerResults.toMap()

    fun calculatePlayerWinLossByParticipant(
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

    fun calculatePlayerWinLoss(
        dealerCards: List<Card>,
        playerCards: List<Card>,
    ): WinLoss {
        val dealerScore = Rule.calculateBestCardValueInRule(dealerCards)
        val playerScore = Rule.calculateBestCardValueInRule(playerCards)

        if (playerScore > 21) {
            dealerResults[WinLoss.WIN] = (dealerResults[WinLoss.WIN] ?: 0) + 1
            return WinLoss.LOSE
        }

        if (dealerScore in (playerScore + 1)..21) {
            dealerResults[WinLoss.WIN] = (dealerResults[WinLoss.WIN] ?: 0) + 1
            return WinLoss.LOSE
        }

        if (dealerScore == playerScore) {
            dealerResults[WinLoss.DRAW] = (dealerResults[WinLoss.DRAW] ?: 0) + 1
            return WinLoss.DRAW
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
