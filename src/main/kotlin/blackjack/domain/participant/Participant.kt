package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.CardScore

abstract class Participant(val name: String) {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    val score: Int
        get() = CardScore.from(_cards).score

    fun receive(card: Card) {
        _cards.add(card)
    }

    fun isBust(): Boolean = score > TARGET_SCORE

    companion object {
        const val INIT_CARD_SIZE = 2
        const val TARGET_SCORE = 21
    }
}
