package blackjack.model

enum class WinningResult {
    WIN,
    LOSE,
    PUSH,
    ;

    companion object {
        fun from(
            targetScore: Int,
            otherScore: Int,
        ): WinningResult =
            when {
                targetScore > otherScore -> WIN
                targetScore < otherScore -> LOSE
                else -> PUSH
            }
    }
}
