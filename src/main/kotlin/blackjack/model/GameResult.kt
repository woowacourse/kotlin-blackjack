package blackjack.model

enum class GameResult {
    PUSH,
    WIN,
    LOSE,
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
