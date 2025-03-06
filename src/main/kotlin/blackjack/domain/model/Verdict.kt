package blackjack.domain.model

enum class Verdict(val value: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    fun reverse(): Verdict {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            else -> DRAW
        }
    }

    companion object {
        fun determine(
            standardPlayer: Dealer,
            comparePlayer: Player,
        ): Verdict {
            return when {
                standardPlayer.isBust() && comparePlayer.isBust() -> LOSE
                standardPlayer.isBust() -> WIN
                standardPlayer.getScore() > comparePlayer.getScore() || comparePlayer.isBust() -> LOSE
                standardPlayer.getScore() < comparePlayer.getScore() && !comparePlayer.isBust() -> WIN
                else -> DRAW
            }
        }
    }
}
