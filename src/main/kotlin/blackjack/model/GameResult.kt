package blackjack.model

enum class GameResult(
    val koreanTitle: String,
) {
    PUSH("무"),
    WIN("승"),
    LOSE("패"),
    ;

    companion object {
        fun of(
            standardScore: Int,
            comparedScore: Int,
        ): GameResult {
            when {
                standardScore == comparedScore -> return PUSH
                standardScore > comparedScore -> return WIN
            }
            return LOSE
        }

        fun reversed(result: GameResult): GameResult =
            when (result) {
                PUSH -> PUSH
                WIN -> LOSE
                LOSE -> WIN
            }
    }
}
