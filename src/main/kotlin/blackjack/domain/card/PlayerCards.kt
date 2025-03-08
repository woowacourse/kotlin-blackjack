package blackjack.domain.card

class PlayerCards(
    val items: Set<TrumpCard>,
) {
    val size get() = items.size

    fun add(card: TrumpCard): PlayerCards {
        return PlayerCards(items + card)
    }

    fun sumOfCards(): Int = items.sumOf { card -> card.tier.values }

    fun hasAce(): Boolean = items.map { it.tier }.contains(Tier.ACE)
}
