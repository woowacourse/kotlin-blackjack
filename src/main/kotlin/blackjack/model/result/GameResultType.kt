package blackjack.model.result

enum class GameResultType(val message: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무"), ;

    fun reverse(): GameResultType {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
        }
    }
}
