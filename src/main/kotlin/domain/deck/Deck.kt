package domain.deck

import domain.card.Card
import domain.card.CardMaker

object Deck {
    private const val ERROR_EMPTY_DECK = "[ERROR] 카드가 존재하지 않습니다."
    private var deck = CardMaker().makeCards()
    private var cardPosition = 0

    fun makeDeck() {
        cardPosition = 0
        deck = deck.shuffled()
    }

    fun giveCard(): Card {
        checkDeckEmpty()
        return deck[cardPosition++]
    }

    private fun checkDeckEmpty() {
        check(deck.isNotEmpty()) { println(ERROR_EMPTY_DECK) }
    }
}
