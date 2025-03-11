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
        return if (cards.hasAce() && (sumOfCards() + ACE_EXTRACT_SCORE > BUST_STANDARD).not()) {
            sumOfCards() + ACE_EXTRACT_SCORE
        } else {
            sumOfCards()
        }
    }

    fun isBust(): Boolean = totalScore() > BUST_STANDARD

    protected fun sumOfCards(): Int = cards.sumOfCards()

    companion object {
        const val DEALER_MUST_REACH_SCORE = 16
        const val ACE_EXTRACT_SCORE = 10
    }
}
