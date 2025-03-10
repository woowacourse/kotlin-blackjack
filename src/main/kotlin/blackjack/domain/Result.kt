package blackjack.domain

enum class Result() {
    WIN,
    LOSE,
    PUSH,
    ;

    companion object {
        fun from(
            targetScore: Int,
            otherScore: Int,
        ): Result =
            when {
                targetScore > otherScore -> WIN
                targetScore < otherScore -> LOSE
                else -> PUSH
            }
    }
}
