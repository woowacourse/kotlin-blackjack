package domain.person

import domain.card.Card
import domain.constant.BlackJackConstants.BLACK_JACK_NUMBER
import domain.constant.GameState
import domain.constant.GameState.BUST
import domain.constant.GameState.HIT
import domain.result.CardsScore.getMinTotalCardNumber
import domain.result.CardsScore.getTotalCardNumber

class Player(value: List<Card>, override val name: String) : Person(value, name) {

    override fun checkState(): GameState {
        return when {
            cards.value.size == 2 && getTotalCardNumber(cards) == BLACK_JACK_NUMBER
            -> GameState.BLACKJACK

            getMinTotalCardNumber(cards) > BLACK_JACK_NUMBER -> BUST
            else -> HIT
        }
    }
}
