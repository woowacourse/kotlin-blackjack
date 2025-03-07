package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.TrumpCard
import blackjack.domain.enums.CardTier

class Dealer : Participant {
    private val _cards = mutableListOf<TrumpCard>()
    override val cards: List<TrumpCard> get() = _cards.toList()

    override val sumOfCards: Int get() = _cards.sumOf { card -> card.tier.values }

    override fun addCard(card: TrumpCard) {
        _cards.add(card)
    }

    override fun hasAce(): Boolean = cards.map { it.tier }.contains(CardTier.ACE)

    override fun isBust(): Boolean = sumOfCards + ACE_EXTRACT_SCORE > BUST_STANDARD

    override fun sum(): Int =
        if (hasAce() && !isBust()) {
            _cards.sumOf { card -> card.tier.values } + ACE_EXTRACT_SCORE
        } else {
            _cards.sumOf { card -> card.tier.values }
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
