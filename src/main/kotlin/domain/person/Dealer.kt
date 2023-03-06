package domain.person

import domain.card.Card
import domain.constant.BlackJackConstants.BLACK_JACK
import domain.constant.BlackJackConstants.DEALER_STAND_CONDITION
import domain.person.GameState.BUST
import domain.person.GameState.HIT
import domain.person.GameState.STAND

class Dealer(override val name: String = "딜러") : Person(name) {
    fun showOneCard(): List<Card> {
        return cards.subList(0, 1)
    }

    override fun checkState() = when {
        getTotalCardNumber() > DEALER_STAND_CONDITION -> STAND
        getTotalCardNumber() > BLACK_JACK -> BUST
        getTotalCardNumber() <= BLACK_JACK -> HIT
        else -> throw IllegalStateException()
    }
}
