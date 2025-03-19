package blackjack.model

enum class GameResult(
    val rate: Double,
) {
    PUSH(0.0),
    WIN(1.0),
    BLACKJACK_WIN(1.5),
    LOSE(-1.0),
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
                BLACKJACK_WIN -> LOSE
            }
    }
}
