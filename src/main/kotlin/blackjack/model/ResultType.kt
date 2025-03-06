package blackjack.model

enum class ResultType(val value: Char) {
    WIN('승'),
    TIE('무'),
    LOSS('패'),
    ;

    companion object {
        fun judgeScore(dealerScore: Int, playerScore: Int): ResultType {
            if (dealerScore < playerScore) return WIN
            if (dealerScore == playerScore) return TIE
            return LOSS
        }
    }
}
