package blackjack.domain.card

class PlayerCards(
    val items: Set<TrumpCard>,
) {
    operator fun plus(card: TrumpCard): PlayerCards {
        return PlayerCards(items + card)
    }

    fun sumOfCards(): Int = items.sumOf { card -> card.tier.values }

    fun hasAce(): Boolean = items.any { it.tier == Tier.ACE }
}
