package blackjack.domain.model

enum class Verdict(val value: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    fun reverse(): Verdict {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            else -> DRAW
        }
    }
}
