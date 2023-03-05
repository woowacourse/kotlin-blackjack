package domain.deck

import domain.card.Card

class Deck(private val deck: MutableList<Card>) {

    fun giveCard(): Card {
        check(deck.size > 0) { println(ERROR_EMPTY_DECK) }
        return deck.removeLast()
    }

    companion object {
        private const val ERROR_EMPTY_DECK = "[ERROR] 카드가 존재하지 않습니다."
    }
}
