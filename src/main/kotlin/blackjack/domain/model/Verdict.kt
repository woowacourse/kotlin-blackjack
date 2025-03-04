package blackjack.domain.model

enum class Verdict(value: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    companion object {
        fun determine(
            dealerScore: Int,
            playerScore: Int,
        ): Verdict {
            return when {
                dealerScore < playerScore -> WIN
                dealerScore > playerScore -> LOSE
                else -> DRAW
            }
        }
    }
}
