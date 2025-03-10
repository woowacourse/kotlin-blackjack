package blackjack.domain

import blackjack.domain.card.CardTier
import blackjack.domain.card.TrumpCard

class ParticipantCards(
    private val _cards: MutableList<TrumpCard> = mutableListOf(),
) {
    val allCards: List<TrumpCard> get() = _cards.toList()
    val sumOfCards: Int get() = _cards.sumOf { it.tier.values }

    fun add(card: TrumpCard) {
        _cards.add(card)
    }

    fun hasAce(): Boolean = _cards.any { it.tier == CardTier.ACE }

    fun size(): Int = _cards.size

    fun first(): TrumpCard = _cards.first()
}
