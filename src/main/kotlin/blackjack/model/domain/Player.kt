package blackjack.model.domain

import blackjack.model.service.Blackjack.Companion.BUST_STANDARD

class Player(val name: String) : Participants {
    override val cards: MutableList<Card> = mutableListOf()
    var alive: Boolean = true

    fun isAlive() {
        if (sumCardNumber > BUST_STANDARD) {
            alive = false
        }
    }

    fun isAlive(number: Int) {
        if (sumCardNumber < number) {
            alive = false
        }
    }
}
