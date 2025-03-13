package blackjack.model

import blackjack.model.GameStatus.BLACKJACK
import blackjack.model.GameStatus.BUST

enum class ResultType(val value: Char) {
    WIN('승'),
    TIE('무'),
    LOSE('패'), ;

    companion object {
        fun judgeForDealer(
            dealer: Dealer,
            player: Player,
        ): ResultType {
            val dealerGameStatus = dealer.gameStatus
            val playerGameStatus = player.gameStatus
            if (playerGameStatus == BUST) return WIN
            if (dealerGameStatus == BLACKJACK && playerGameStatus == BLACKJACK) return TIE

            val dealerFinalScore = ScoreCalculator.calculateFinalScore(dealer.cards)
            val playerFinalScore = ScoreCalculator.calculateFinalScore(player.cards)
            if (playerFinalScore < dealerFinalScore) return WIN
            if (playerFinalScore == dealerFinalScore) return TIE
            return LOSE
        }

        fun judgeForPlayer(
            player: Player,
            dealer: Dealer,
        ): ResultType {
            val dealerGameStatus = dealer.gameStatus
            val playerGameStatus = player.gameStatus
            if (playerGameStatus == BUST) return LOSE
            if (dealerGameStatus == BLACKJACK && playerGameStatus == BLACKJACK) return TIE

            val dealerFinalScore = ScoreCalculator.calculateFinalScore(dealer.cards)
            val playerFinalScore = ScoreCalculator.calculateFinalScore(player.cards)
            if (dealerFinalScore < playerFinalScore) return WIN
            if (dealerFinalScore == playerFinalScore) return TIE
            return LOSE
        }
    }
}
