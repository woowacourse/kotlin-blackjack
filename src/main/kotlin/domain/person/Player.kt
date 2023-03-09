package domain.person

import domain.card.Card
import domain.constant.BlackJackConstants.BLACK_JACK_NUMBER
import domain.constant.GameState
import domain.constant.GameState.BUST
import domain.constant.GameState.HIT
import domain.result.CardsScore.getTotalWithOneBigAce
import domain.result.CardsScore.getTotalWithSmallAce

class Player(value: List<Card>, override val name: String) : Person(value, name) {

    override fun checkState(): GameState {
        return when {
            cards.value.size == 2 && getTotalWithOneBigAce(cards) == BLACK_JACK_NUMBER
            -> GameState.BLACKJACK

            getTotalWithSmallAce(cards) > BLACK_JACK_NUMBER -> BUST
            else -> HIT
        }
    }
}
