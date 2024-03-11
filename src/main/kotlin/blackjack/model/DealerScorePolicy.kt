package blackjack.model

class DealerScorePolicy : ScorePolicy {
    override fun score(cards: Cards): Int =
        when {
            cards.isBlackJack() -> ScorePolicy.BLACKJACK
            cards.isBusted() -> BUSTED_SCORE
            else -> cards.sum()
        }

    companion object {
        private const val BUSTED_SCORE = 0
    }
}
