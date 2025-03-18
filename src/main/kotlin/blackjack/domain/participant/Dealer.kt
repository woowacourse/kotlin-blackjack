package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.CARD_COUNT_OF_DEALER_MUST_INITIAL_OPEN
import blackjack.domain.GameResult
import blackjack.domain.GameResult.BLACKJACK
import blackjack.domain.GameResult.LOSE
import blackjack.domain.GameResult.PUSH
import blackjack.domain.GameResult.WIN
import blackjack.domain.card.TrumpCard

class Dealer : Participant() {
    override fun canHit(): Boolean {
        return totalScore() <= DEALER_MUST_REACH_SCORE
    }

    override fun getInitialCards(): Set<TrumpCard> {
        return cards.items.take(CARD_COUNT_OF_DEALER_MUST_INITIAL_OPEN).toSet()
    }

    override fun compare(other: Participant): GameResult {
        return when {
            hasBlackJack() && !other.hasBlackJack() -> BLACKJACK
            hasBlackJack() && other.hasBlackJack() -> PUSH
            isBust() && other.isBust() -> WIN
            isBust() -> LOSE
            other.isBust() -> WIN
            totalScore() > other.totalScore() -> WIN
            totalScore() < other.totalScore() -> LOSE
            else -> PUSH
        }
    }
}
