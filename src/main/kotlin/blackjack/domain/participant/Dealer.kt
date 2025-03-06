package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.TrumpCard
import blackjack.domain.enums.CardTier

class Dealer : Participant {
    private val _cards = mutableListOf<TrumpCard>()
    override val cards: List<TrumpCard> get() = _cards.toList()

    override fun getInitializedCards(
        initializedCardCount: Int,
        card: TrumpCard,
    ) {
        repeat(initializedCardCount) {
            addCard(card)
        }
    }

    override fun addCard(card: TrumpCard) {
        _cards.add(card)
    }

    override fun hasAce(): Boolean = cards.map { it.tier }.contains(CardTier.ACE)

    override fun isBust(): Boolean = _cards.sumOf { card -> card.tier.values.first() } > BUST_STANDARD

    fun sum() = _cards.sumOf { card -> card.first() }
}
