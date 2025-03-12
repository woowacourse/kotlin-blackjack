package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.card.PlayerCards
import blackjack.domain.card.TrumpCard

abstract class Participant {
    protected var cards = PlayerCards(emptySet())

    fun addCard(card: TrumpCard) {
        cards = cards.add(card)
    }

    fun getCards(): Set<TrumpCard> = cards.items

    fun totalScore(): Int {
        val sumOfCards = cards.sumOfCards()
        return if (cards.hasAce() && (sumOfCards + ACE_EXTRACT_SCORE > BUST_STANDARD).not()) {
            sumOfCards + ACE_EXTRACT_SCORE
        } else {
            sumOfCards
        }
    }

    abstract fun isDrawable(): Boolean

    companion object {
        const val DEALER_MUST_REACH_SCORE = 16
        const val ACE_EXTRACT_SCORE = 10
    }
}
