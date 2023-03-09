package domain.deck

import domain.card.Card

class Deck(private var deck: List<Card>) {

    fun makeRandomDeck() {
        deck = deck.shuffled()
        cardPosition = 0
    }

    fun giveCard(): Card {
        checkDeckEmpty()
        return deck[cardPosition++]
    }

    private fun checkDeckEmpty() {
        check(deck.isNotEmpty()) { println(ERROR_EMPTY_DECK) }
    }

    companion object {
        private var cardPosition = 0
        private const val ERROR_EMPTY_DECK = "[ERROR] 카드가 존재하지 않습니다."
    }
}
