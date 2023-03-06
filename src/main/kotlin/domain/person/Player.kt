package domain.person

import domain.constant.BlackJackConstants.BLACK_JACK
import domain.person.GameState.BUST
import domain.person.GameState.HIT
import domain.result.CardsScore

class Player(override val name: String) : Person(name) {

    override fun checkState(): GameState {
        if (CardsScore.getMinTotalCardNumber(cards) > BLACK_JACK) {
            return BUST
        }
        return HIT
    }
}
