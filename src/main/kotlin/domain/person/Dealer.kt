package domain.person

import domain.card.Card
import domain.constant.BlackJackConstants.BLACK_JACK
import domain.constant.BlackJackConstants.DEALER_STAND_CONDITION
import domain.constant.GameState
import domain.constant.GameState.BUST
import domain.constant.GameState.HIT
import domain.constant.GameState.STAND
import domain.result.CardsScore

class Dealer(value: List<Card>, override val name: String = "딜러") : Person(value, name) {
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
