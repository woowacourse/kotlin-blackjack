package domain.deck

import domain.card.Card

class Deck(private var deck: List<Card>) {

    fun makeRandomDeck(randomDeck: List<Card>) {
        deck = randomDeck
    }

    fun giveCard(): Card {
        checkDeckEmpty()
        val card = deck.first()
        deck = deck.minus(card)
        return card
    }

    private fun checkDeckEmpty() {
        check(deck.isNotEmpty()) { println(ERROR_EMPTY_DECK) }
    }

    companion object {
        private const val ERROR_EMPTY_DECK = "[ERROR] 카드가 존재하지 않습니다."
    }
}
