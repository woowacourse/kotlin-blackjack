package blackjack.model

enum class Result {
    DEALER_WIN,
    PLAYER_WIN,
    TIE, ;

    fun convertResult(): String {
        return when (this) {
            Result.PLAYER_WIN -> "승"
            Result.TIE -> "무"
            Result.DEALER_WIN -> "패"
        }
    }
}
