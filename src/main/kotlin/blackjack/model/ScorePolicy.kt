package blackjack.model

interface ScorePolicy {
    fun score(cards: Cards): Int

    companion object {
        const val BLACKJACK_SCORE = 22
    }
}
