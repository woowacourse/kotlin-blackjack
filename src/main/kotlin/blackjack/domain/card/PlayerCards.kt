package blackjack.domain.card

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.BlackJackGame.Companion.INITIAL_CARD_COUNT

class PlayerCards(
    val items: Set<TrumpCard>,
) {
    operator fun plus(card: TrumpCard): PlayerCards {
        return PlayerCards(items + card)
    }

    fun hasBlackJack(score: Int): Boolean {
        return items.size == INITIAL_CARD_COUNT && (score) == BUST_STANDARD
    }

    fun sumOfCards(): Int = items.sumOf { card -> card.tier.values }

    fun hasAce(): Boolean = items.any { it.tier == Tier.ACE }
}
