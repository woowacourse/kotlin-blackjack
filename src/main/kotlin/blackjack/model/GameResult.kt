package blackjack.model

enum class GameResult(val message: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무"), ;

    fun reverse(): GameResult {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
        }
    }
}
