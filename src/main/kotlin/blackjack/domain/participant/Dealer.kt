package blackjack.domain.participant

import blackjack.domain.GameResult
import blackjack.domain.ParticipantCards
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Player.Companion.DRAW_MULTIPLY
import blackjack.domain.participant.Player.Companion.LOSE_MULTIPLY
import blackjack.domain.participant.Player.Companion.WIN_MULTIPLY

class Dealer(
    cards: ParticipantCards,
) : Participant(cards) {
    override fun showInitialCards(): List<TrumpCard> = takeCards(DEALER_INITIAL_CARD_COUNT)

    override fun isDrawable(): Boolean {
        if (cards.hasAce() && !cards.isBust(ACE_SOFT_SCORE)) {
            return cards.sumOfCards + ACE_SOFT_SCORE <= DEALER_MAX_SCORE
        }
        return cards.sumOfCards <= DEALER_MAX_SCORE
    }

    override fun getResult(other: Participant): GameResult {
        val myScore = cards.finalScore()
        val otherScore = other.cards.finalScore()

        return when {
            !cards.isBlackJack() && other.cards.isBlackJack() -> GameResult.LOSE
            cards.isBlackJack() && !other.cards.isBlackJack() -> GameResult.BLACKJACK
            other.cards.isBust() -> GameResult.WIN
            this.cards.isBust() && !other.cards.isBust() -> GameResult.LOSE
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
        private const val ACE_SOFT_SCORE = 10
    }
}
