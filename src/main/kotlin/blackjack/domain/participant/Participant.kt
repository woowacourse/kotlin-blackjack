package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.card.PlayerCards
import blackjack.domain.card.TrumpCard

abstract class Participant {
    private var _cards = PlayerCards(emptySet())
    val cards get() = _cards

    fun addCard(card: TrumpCard) {
        _cards = _cards.add(card)
    }

    fun totalScore(): Int {
        return if (hasAce() && (sumOfCards() + ACE_EXTRACT_SCORE > BUST_STANDARD).not()) {
            sumOfCards() + ACE_EXTRACT_SCORE
        } else {
            sumOfCards()
        }
    }

    fun isBust(): Boolean = totalScore() > BUST_STANDARD

    fun hasAce(): Boolean = _cards.hasAce()

    protected fun sumOfCards(): Int = cards.sumOfCards()

    companion object {
        const val DEALER_MAX_SCORE = 16
        const val ACE_EXTRACT_SCORE = 10
    }
}
