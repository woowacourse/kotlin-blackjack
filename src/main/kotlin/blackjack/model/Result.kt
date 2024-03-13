package blackjack.model

enum class Result {
    WIN,
    DRAW,
    LOSE, ;

    fun reverse(): Result {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
        }
    }
}
