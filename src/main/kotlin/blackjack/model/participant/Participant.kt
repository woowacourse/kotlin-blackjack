package blackjack.model.participant

import blackjack.model.GameManager.Companion.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.model.ResultCalculator
import blackjack.model.ResultCalculator.BLACKJACK_NUMBER
import blackjack.model.card.Card

abstract class Participant(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards.map { it.copy() }.toList()

    val score: Int
        get() = ResultCalculator.calculate(cards)

    fun isBlackjack(): Boolean {
        return _cards.size == INITIAL_HAND_OUT_CARD_COUNT && score == BLACKJACK_NUMBER
    }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun isBust(): Boolean = score > BLACKJACK_NUMBER

    abstract fun getInitialCard(): List<Card>
}
