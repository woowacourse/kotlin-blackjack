package blackjack.domain.card

class PlayerCards(
    val items: Set<TrumpCard>,
) {
    fun add(card: TrumpCard): PlayerCards {
        return PlayerCards(items + card)
    }

    fun size() = items.size

    fun first(): TrumpCard = items.first()

    fun sumOfCards(): Int = items.sumOf { card -> card.tier.values }

    fun hasAce(): Boolean = items.map { it.tier }.contains(Tier.ACE)
}
