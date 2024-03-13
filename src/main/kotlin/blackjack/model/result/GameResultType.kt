package blackjack.model.result

enum class GameResultType {
    WIN,
    LOSE,
    DRAW, ;

    fun reverse(): GameResultType {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
        }
    }
}
