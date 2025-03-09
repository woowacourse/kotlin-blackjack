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
                standardPlayer.isBusted() && comparePlayer.isBusted() -> LOSE
                standardPlayer.isBusted() -> WIN
                standardPlayer.computeScore() > comparePlayer.computeScore() || comparePlayer.isBusted() -> LOSE
                standardPlayer.computeScore() < comparePlayer.computeScore() && !comparePlayer.isBusted() -> WIN
                else -> DRAW
            }
        }
    }
}
