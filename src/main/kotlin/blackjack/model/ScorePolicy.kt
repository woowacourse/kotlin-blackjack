package blackjack.model

interface ScorePolicy {
    fun score(cards: Cards): Int

    companion object {
        @JvmStatic
        val BLACKJACK = 22
    }
}
