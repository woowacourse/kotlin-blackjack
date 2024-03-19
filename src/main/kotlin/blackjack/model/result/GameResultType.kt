package blackjack.model.result

enum class GameResultType(val rate: Double) {
    BLACKJACK(1.5),
    WIN(1.0),
    LOSE(-1.0),
    DRAW(0.0), ;

    fun reverse(): GameResultType {
        return when (this) {
            BLACKJACK -> BLACKJACK
            WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
        }
    }
}
