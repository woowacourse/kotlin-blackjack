package blackjack.model.domain

import blackjack.model.service.Blackjack.Companion.BUST_STANDARD

interface Participants {
    val cards: MutableList<Card>
    var alive: Boolean

    val sumCardNumber get() = cards.sumOf { it.cardNumber.number }

    val cardDeck get() = cards.toList()

    fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun isAlive() {
        if (sumCardNumber > BUST_STANDARD) {
            alive = false
        }
    }
}
