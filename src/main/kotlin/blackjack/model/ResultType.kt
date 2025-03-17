package blackjack.model

enum class ResultType {
    WIN,
    TIE,
    LOSS,
    BLACKJACK, ;

    companion object {
        const val BLACKJACK_PROFIT_MULTIPLIER = 1.5
        const val LOSS_PROFIT_MULTIPLIER = -1.0
        const val TIE_PROFIT_MULTIPLIER = 0.0
    }
}
