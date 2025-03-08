package blackjack.domain.participant

import blackjack.domain.card.PlayerCards
import blackjack.domain.card.TrumpCard

abstract class Participant {
    private var _cards = PlayerCards(emptySet())
    val cards get() = _cards

    fun cardSize(): Int = _cards.size()

    fun addCard(card: TrumpCard) {
        _cards = _cards.add(card)
    }

    fun hasAce(): Boolean = _cards.hasAce()

    protected fun sumOfCards(): Int = cards.sumOfCards()

    abstract fun isBust(): Boolean

    abstract fun totalScore(): Int

    companion object {
        const val DEALER_MAX_SCORE = 16
        const val ACE_EXTRACT_SCORE = 10
    }
}
