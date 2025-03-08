package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.card.PlayerCards
import blackjack.domain.card.TrumpCard

class Dealer : Participant {
    private val _cards = mutableListOf<TrumpCard>()
    override val cards: List<TrumpCard> get() = _cards.toList()

    private var _cards2 = PlayerCards(emptySet())
    val cards2 get() = _cards2

    override val sumOfCards: Int get() = _cards2.sumOfCards

    override fun addCard(card: TrumpCard) {
        _cards2 = cards2.add(card)
    }

    override fun hasAce(): Boolean = _cards2.hasAce()

    override fun isBust(): Boolean = sumOfCards + ACE_EXTRACT_SCORE > BUST_STANDARD

    override fun sum(): Int =
        if (hasAce() && !isBust()) {
            sumOfCards + ACE_EXTRACT_SCORE
        } else {
            sumOfCards
        }

    fun isOverMaxScore(): Boolean {
        if (hasAce() && !isBust()) {
            return sumOfCards + ACE_EXTRACT_SCORE > DEALER_MAX_SCORE
        }
        return sumOfCards > DEALER_MAX_SCORE
    }

    companion object {
        const val DEALER_MAX_SCORE = 16
        const val ACE_EXTRACT_SCORE = 10
    }
}
