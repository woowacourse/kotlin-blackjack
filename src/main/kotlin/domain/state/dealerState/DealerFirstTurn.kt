package domain.state.dealerState

import domain.card.Card
import domain.card.Hand
import domain.constant.BlackJackConstants.BLACK_JACK_NUMBER
import domain.constant.BlackJackConstants.DEALER_STAND_CONDITION
import domain.result.Score
import domain.state.Started
import domain.state.State

class DealerFirstTurn(value: Hand) : Started(value) {
    override fun draw(card: Card): State {
        val score = Score.of(hand.apply { add(card) })
        return when {
            hand.value.size == 1 -> DealerFirstTurn(hand)
            score.value == BLACK_JACK_NUMBER -> DealerBlackJack(hand)
            score.value > DEALER_STAND_CONDITION -> DealerStay(hand)
            else -> DealerHit(hand)
        }
    }
}
