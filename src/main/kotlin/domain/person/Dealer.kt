package domain.person

import domain.card.Card
import domain.constant.BLACK_JACK
import domain.constant.DEALER_STAND_CONDITION
import domain.person.GameState.BUST
import domain.person.GameState.HIT
import domain.person.GameState.STAND

class Dealer(override val name: String = "딜러") : Person(name) {
    fun showOneCard(): List<Card> {
        return cards.subList(0, 1)
    }

    override fun checkState(): GameState {
        if (getTotalCardNumber() > DEALER_STAND_CONDITION) {
            return STAND
        }
        if (getTotalCardNumber() > BLACK_JACK) {
            return BUST
        }
        return HIT
    }
}
