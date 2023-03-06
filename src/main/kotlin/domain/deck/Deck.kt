package domain.deck

import domain.card.Card

class Deck(private var deck: List<Card>) {

    fun giveCard(): Card {
        check(deck.isNotEmpty()) { println(ERROR_EMPTY_DECK) }
        val card = deck.last()
        deck = deck.minus(card)
        return card
    }

    companion object {
        private const val ERROR_EMPTY_DECK = "[ERROR] 카드가 존재하지 않습니다."
    }
}
