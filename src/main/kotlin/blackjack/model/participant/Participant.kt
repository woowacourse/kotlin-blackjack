package blackjack.model.participant

import blackjack.model.GameManager.Companion.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.model.ResultCalculator
import blackjack.model.ResultCalculator.BLACKJACK_NUMBER
import blackjack.model.amount.BetAmount
import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.Shape

abstract class Participant(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards get() = _cards.toList()

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
