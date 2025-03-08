package blackjack.enums

import blackjack.domain.Score

enum class Result {
    WIN,
    LOSE,
    PUSH,
    ;

    companion object {
        fun from(
            targetScore: Score,
            otherScore: Score,
        ): Result =
            when {
                targetScore > otherScore -> WIN
                targetScore < otherScore -> LOSE
                else -> PUSH
            }
    }
}
