package blackjack.enums

enum class Result(
    val message: String,
) {
    WIN("승"),
    LOSE("패"),
    PUSH("무"),
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
