package blackjack.domain.model.hand

import blackjack.domain.model.Card
import blackjack.domain.model.Score

class Hands(private val _cards: List<Card>) {
    constructor(vararg card: Card) : this(card.toList())

    val cards get() = _cards.map { it.copy() }

    private val size get() = _cards.size

    fun extractCards(count: Int): List<Card> = cards.take(count)

    fun nextHand(card: Card) = Hands(cards + card)

    fun score() = Score(cards)

    fun isBlackJack() = score().isMaxScore() && size == START_CARD_COUNT

    fun isBustScore() = score().isBustScore()

    companion object {
        private const val START_CARD_COUNT = 2
    }
}
