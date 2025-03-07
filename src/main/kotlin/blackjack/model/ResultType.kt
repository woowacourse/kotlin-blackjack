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
            val dealerFinalScore = if (dealer.isBust()) 0 else dealer.calculateTotalScore()
            val playerFinalScore = if (player.isBust()) 0 else player.calculateTotalScore()
            if (dealerFinalScore < playerFinalScore) return WIN
            if (dealerFinalScore == playerFinalScore) return TIE
            return LOSS
        }

        const val BUST_NUMBER = 21
    }
}
