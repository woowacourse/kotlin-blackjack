package blackjack.domain.model

import blackjack.domain.model.card.Card

class GameResultRecord {
    private val dealerResults = mutableMapOf(GameResult.WIN to 0, GameResult.LOSE to 0, GameResult.DRAW to 0)

    fun calculatePlayerWinLoss(
        dealerCards: List<Card>,
        playerCards: List<Card>,
    ): GameResult {
        val dealerScore = Rule.calculateResultByCards(dealerCards)
        val playerScore = Rule.calculateResultByCards(playerCards)

        if (playerScore > 21) {
            dealerResults[GameResult.WIN] = (dealerResults[GameResult.WIN] ?: 0) + 1
            return GameResult.LOSE
        }

        if (dealerScore in (playerScore + 1)..21) {
            dealerResults[GameResult.WIN] = (dealerResults[GameResult.WIN] ?: 0) + 1
            return GameResult.LOSE
        }

        if (dealerScore == playerScore) {
            dealerResults[GameResult.DRAW] = (dealerResults[GameResult.DRAW] ?: 0) + 1
            return GameResult.DRAW
        }
        dealerResults[GameResult.LOSE] = (dealerResults[GameResult.LOSE] ?: 0) + 1
        return GameResult.WIN
    }

    fun getDealerWinLossText(): String {
        val dealerWinText =
            if (dealerResults[GameResult.WIN] != 0) {
                dealerResults[GameResult.WIN].toString() + "승 "
            } else {
                ""
            }
        val dealerDrawText =
            if (dealerResults[GameResult.DRAW] != 0) {
                dealerResults[GameResult.DRAW].toString() + "무 "
            } else {
                ""
            }
        val dealerLossText =
            if (dealerResults[GameResult.LOSE] != 0) {
                dealerResults[GameResult.LOSE].toString() + "패 "
            } else {
                ""
            }

        return (dealerWinText + dealerDrawText + dealerLossText).trim()
    }
}
