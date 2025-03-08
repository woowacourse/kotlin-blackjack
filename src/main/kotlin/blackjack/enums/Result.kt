package blackjack.enums

import blackjack.domain.Score

enum class Result(
    val message: String,
) {
    WIN("승"),
    LOSE("패"),
    PUSH("무"),
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
