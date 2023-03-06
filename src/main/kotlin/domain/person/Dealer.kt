package domain.person

import domain.card.Card
import domain.constant.BlackJackConstants.BLACK_JACK
import domain.constant.BlackJackConstants.DEALER_STAND_CONDITION
import domain.person.GameState.BUST
import domain.person.GameState.HIT
import domain.person.GameState.STAND
import domain.result.CardsScore

class Dealer(override val name: String = "딜러") : Person(name) {
    fun showOneCard(): List<Card> {
        return cards.value.subList(0, 1)
    }

    override fun checkState(): GameState {
        val totalNumber = CardsScore.getTotalCardNumber(cards)
        return when {
            totalNumber > BLACK_JACK -> BUST
            totalNumber > DEALER_STAND_CONDITION -> STAND
            totalNumber <= BLACK_JACK -> HIT
            else -> throw IllegalStateException()
        }
    }
}
