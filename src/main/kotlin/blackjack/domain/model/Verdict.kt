package blackjack.domain.model

enum class Verdict(value: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    companion object {
        fun determine(
            standardScore: Int,
            compareScore: Int,
        ): Verdict {
            return when {
                standardScore > compareScore -> WIN
                standardScore < compareScore -> LOSE
                else -> DRAW
            }
        }
    }
}
