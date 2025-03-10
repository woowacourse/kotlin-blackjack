package blackjack.domain

import blackjack.domain.card.CardTier
import blackjack.domain.card.TrumpCard

class ParticipantCards(
    private val _cards: MutableList<TrumpCard> = mutableListOf(), // 내부에서 관리
) {
    val allCards: List<TrumpCard> get() = _cards.toList() // 외부에서는 불변 리스트로 접근하게 함
    val sumOfCards: Int get() = _cards.sumOf { it.tier.values }

    fun add(card: TrumpCard) {
        _cards.add(card)
    }

    fun hasAce(): Boolean = _cards.any { it.tier == CardTier.ACE }

    fun size(): Int = _cards.size

    fun first(): TrumpCard = _cards.first()
}
