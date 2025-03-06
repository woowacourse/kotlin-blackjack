package blackjack.model.domain

import blackjack.model.service.Blackjack.Companion.BUST_STANDARD

interface Participants {
    val name: String
    val cards: MutableList<Card>
    var alive: Boolean

    val sumCardNumber: Int get() = getSumNumber()
    val cardDeck get() = cards.toList()

    private fun getSumNumber(): Int {
        var sum = cards.sumOf { it.cardNumber.number }
        val aCount: Int = cards.count { it.cardNumber == CardNumber.Ace }

        repeat(aCount) {
            if (sum + 10 >= BUST_STANDARD) return@repeat
            sum += 10
        }

        return sum
    }

    fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun isAlive() {
        if (sumCardNumber > BUST_STANDARD) {
            alive = false
        }
    }
}
