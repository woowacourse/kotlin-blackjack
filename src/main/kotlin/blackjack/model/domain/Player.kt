package blackjack.model.domain

import blackjack.model.domain.Blackjack.Companion.BUSTSTANDARD

class Player(val name: String) : Participants {
    override val cards: MutableList<Card> = mutableListOf()
    var alive: Boolean = true

    fun judgeBust() {
        if (sumCardNumber > BUSTSTANDARD) {
            alive = false
        }
    }
}
