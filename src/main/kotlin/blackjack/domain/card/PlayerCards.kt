package blackjack.domain.card

class PlayerCards(
    val items: Set<TrumpCard>,
) {
    val sumOfCards get() = items.sumOf { card -> card.tier.values }

    fun add(card: TrumpCard): PlayerCards {
        return PlayerCards(items + card)
    }

    fun hasAce(): Boolean = items.map { it.tier }.contains(Tier.ACE)
}
