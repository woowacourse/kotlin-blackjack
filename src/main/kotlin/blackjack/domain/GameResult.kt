package blackjack.domain

enum class GameResult {
    WIN,
    LOSE,
    PUSH,
    ;

    companion object {
        fun from(
            targetScore: Int,
            otherScore: Int,
        ): GameResult {
            return when {
                targetScore > otherScore -> WIN
                targetScore < otherScore -> LOSE
                else -> PUSH
            }
        }
    }
}
