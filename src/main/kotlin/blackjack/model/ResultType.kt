package blackjack.model

enum class ResultType(val value: Char) {
    WIN('승'),
    TIE('무'),
    LOSS('패'), ;

    companion object {
        fun judgeScore(
            dealer: Dealer,
            player: Player,
        ): ResultType {
            val dealerFinalScore = ScoreCalculator.calculateFinalScore(dealer.cards)
            val playerFinalScore = ScoreCalculator.calculateFinalScore(player.cards)
            if (dealerFinalScore < playerFinalScore) return WIN
            if (dealerFinalScore == playerFinalScore) return TIE
            return LOSS
        }
    }
}
