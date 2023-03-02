package domain.person

import domain.card.Card
import domain.constant.DEALER_STAND_CONDITION

class Dealer : Person() {
    fun showOneCard(): List<Card> {
        return cards.subList(0, 1)
    }

    override fun checkState() {
        if (calculateTotalCardNumber() >= DEALER_STAND_CONDITION) {
            gameState = GameState.STAND
        }
    }

    override val name: String = "딜러"
}
