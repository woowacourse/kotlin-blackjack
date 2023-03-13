package domain.deck

import domain.card.Card

class Deck(private val deck: List<Card>) {
    private val _deck: MutableList<Card> = deck.toMutableList()

    init {
        check(!checkDeckEmpty()) { println(ERROR_EMPTY_DECK) }
    }

    fun giveCard(): Card {
        if (checkDeckEmpty()) {
            fillCards()
        }
        val card = _deck.first()
        _deck.remove(card)
        return card
    }

    private fun fillCards() {
        _deck.clear()
        _deck.addAll(Card.getAllCard().shuffled())
    }

    private fun checkDeckEmpty(): Boolean = deck.isEmpty()

    companion object {
        private const val ERROR_EMPTY_DECK = "[ERROR] 카드가 존재하지 않습니다."
    }
}
