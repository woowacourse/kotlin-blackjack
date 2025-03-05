package blackjack

import java.lang.IllegalStateException

class Player : Participant {
    var totalSum: Int = 0
        private set
    val cards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        if (!canHit()) throw IllegalStateException()
        cards.add(card)

        addNumber()
    }

    fun addNumber() {

    }
    fun canHit(): Boolean {
        return totalSum < 21
    }
}
