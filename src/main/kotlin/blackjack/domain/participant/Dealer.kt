package blackjack.domain.participant

import blackjack.domain.GameResult
import blackjack.domain.ParticipantCards
import blackjack.domain.card.TrumpCard

class Dealer(
    cards: ParticipantCards,
) : Participant(cards) {
    override fun showInitialCards(): List<TrumpCard> = takeCards(DEALER_INITIAL_CARD_COUNT)

    override fun isDrawable(): Boolean {
        if (cards.hasAce() && !isBust(ACE_SOFT_SCORE)) {
            return cards.sumOfCards + ACE_SOFT_SCORE <= DEALER_MAX_SCORE
        }
        return cards.sumOfCards <= DEALER_MAX_SCORE
    }

    override fun getResult(other: Participant): GameResult {
        val myScore = this.finalScore()
        val otherScore = other.finalScore()

        return when {
            !isBlackJack() && other.isBlackJack() -> GameResult.LOSE
            isBlackJack() && !other.isBlackJack() -> GameResult.BLACKJACK
            other.isBust() -> GameResult.WIN
            this.isBust() && !other.isBust() -> GameResult.LOSE
            myScore > otherScore -> GameResult.WIN
            myScore < otherScore -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }

    override fun getProfit(gameResult: GameResult): Double =
        when (gameResult) {
            GameResult.BLACKJACK -> DEALER_BLACKJACK_MULTIPLY
            GameResult.WIN -> WIN_MULTIPLY
            GameResult.DRAW -> DRAW_MULTIPLY
            else -> LOSE_MULTIPLY
        }

    companion object {
        private const val DEALER_MAX_SCORE = 16
        private const val DEALER_INITIAL_CARD_COUNT = 1
        private const val DEALER_BLACKJACK_MULTIPLY = 1.0
    }
}
