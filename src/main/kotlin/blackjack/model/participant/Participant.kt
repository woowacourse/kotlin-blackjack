package blackjack.model.participant

import blackjack.model.GameManager.Companion.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.model.ResultCalculator
import blackjack.model.ResultCalculator.BLACKJACK_NUMBER
import blackjack.model.card.Card
import blackjack.model.card.CardNumber

abstract class Participant(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards get() = _cards.toList()

    fun isBlackjack(): Boolean {
        return _cards.size == INITIAL_HAND_OUT_CARD_COUNT && adjustScore == BLACKJACK_NUMBER
    }

    fun addCard(card: Card) = _cards.add(card)

    abstract fun getInitialCard(): List<Card>

    abstract fun isBust(): Boolean
}
