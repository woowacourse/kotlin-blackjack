package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.GameResult
import blackjack.domain.GameResult.BLACKJACK
import blackjack.domain.GameResult.LOSE
import blackjack.domain.GameResult.PUSH
import blackjack.domain.GameResult.WIN
import blackjack.domain.card.PlayerCards
import blackjack.domain.card.TrumpCard

sealed class Participant {
    private var _cards = PlayerCards(emptySet())
    val cards: PlayerCards
        get() = _cards.deepCopy()

    fun addCard(newCard: TrumpCard) {
        _cards = cards + newCard
    }

    fun compare(other: Participant): GameResult {
        return when {
            hasBlackJack() && !other.hasBlackJack() -> BLACKJACK
            hasBlackJack() && other.hasBlackJack() -> PUSH
            isBust() && other.isBust() -> {
                when (other) {
                    is Player -> WIN
                    is Dealer -> LOSE
                }
            }
            isBust() -> LOSE
            other.isBust() -> WIN
            totalScore() > other.totalScore() -> WIN
            totalScore() < other.totalScore() -> LOSE
            else -> PUSH
        }
    }

    fun totalScore(): Int {
        val sumOfCards = cards.sumOfCards()
        return if (cards.hasAce() && (sumOfCards + ACE_EXTRACT_SCORE > BUST_STANDARD).not()) {
            sumOfCards + ACE_EXTRACT_SCORE
        } else {
            sumOfCards
        }
    }

    private fun hasBlackJack(): Boolean {
        return cards.hasBlackJack(totalScore())
    }

    private fun isBust(): Boolean = totalScore() > BUST_STANDARD

    abstract fun getInitialCards(): Set<TrumpCard>

    abstract fun canHit(): Boolean

    private fun PlayerCards.deepCopy(): PlayerCards = PlayerCards(this.items.map { it.copy() }.toSet())

    companion object {
        const val DEALER_MUST_REACH_SCORE = 16
        const val ACE_EXTRACT_SCORE = 10
    }
}
