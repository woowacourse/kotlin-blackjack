package blackjack.model.participant

import blackjack.model.GameManager.Companion.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.model.card.Card
import blackjack.model.card.CardNumber

abstract class Participant(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards get() = _cards.toList()

    fun isBlackjack(): Boolean {
        if (_cards.size != INITIAL_HAND_OUT_CARD_COUNT) return false
        val numbers = _cards.map { it.number }
        return CardNumber.ACE in numbers &&
            numbers.any {
                it in
                    listOf(
                        CardNumber.TEN,
                        CardNumber.JACK,
                        CardNumber.QUEEN,
                        CardNumber.KING,
                    )
            }
    }

    fun addCard(card: Card) = _cards.add(card)

    abstract fun getInitialCard(): List<Card>

    abstract fun isBust(): Boolean
}
