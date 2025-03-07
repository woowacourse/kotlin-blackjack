package blackjack.model

enum class ResultType(val value: Char) {
    WIN('승'), TIE('무'), LOSS('패'), ;

    companion object {
        fun judgeScore(
            dealerScore: Int,
            playerScore: Int,
        ): ResultType {
            val dealerFinalScore = if (dealerScore > 21) 0 else dealerScore
            val playerFinalScore = if (playerScore > 21) 0 else playerScore
            if (dealerFinalScore < playerFinalScore) return WIN
            if (dealerFinalScore == playerFinalScore) return TIE
            return LOSS
        }
    }
}
