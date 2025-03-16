package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.GameResult
import blackjack.domain.card.PlayerCards
import blackjack.domain.card.TrumpCard

sealed class Participant {
    private var _cards = PlayerCards(emptySet())
    val cards: PlayerCards
        get() = _cards.deepCopy()

    fun addCard(newCard: TrumpCard) {
        _cards = cards + newCard
    }

    fun totalScore(): Int {
        val sumOfCards = cards.sumOfCards()
        return if (cards.hasAce() && (sumOfCards + ACE_EXTRACT_SCORE > BUST_STANDARD).not()) {
            sumOfCards + ACE_EXTRACT_SCORE
        } else {
            sumOfCards
        }
    }

    fun hasBlackJack(): Boolean = cards.hasBlackJack(totalScore())

    fun isBust(): Boolean = totalScore() > BUST_STANDARD

    abstract fun compare(other: Participant): GameResult

    abstract fun getInitialCards(): Set<TrumpCard>

    abstract fun canHit(): Boolean

    private fun PlayerCards.deepCopy(): PlayerCards = PlayerCards(this.items.map { it.copy() }.toSet())

    companion object {
        const val DEALER_MUST_REACH_SCORE = 16
        const val ACE_EXTRACT_SCORE = 10
    }
}
