package domain.person

import domain.card.Card
import domain.constant.BlackJackConstants.BLACK_JACK
import domain.constant.GameState
import domain.constant.GameState.BUST
import domain.constant.GameState.HIT
import domain.result.CardsScore

class Player(value: List<Card>, override val name: String) : Person(value, name) {

    override fun checkState(): GameState {
        if (CardsScore.getMinTotalCardNumber(cards) > BLACK_JACK) {
            return BUST
        }
        return HIT
    }
}
