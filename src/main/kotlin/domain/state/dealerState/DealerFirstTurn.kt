package domain.state.dealerState

import domain.card.Card
import domain.card.Hand
import domain.constant.BlackJackConstants.BLACK_JACK_NUMBER
import domain.constant.BlackJackConstants.DEALER_STAND_CONDITION
import domain.result.Score
import domain.state.BlackJack
import domain.state.Started
import domain.state.State
import domain.state.Stay

class DealerFirstTurn(value: Hand) : Started(value) {
    override fun draw(card: Card): State {
        val score = Score.of(hand.apply { add(card) })
        return when {
            hand.value.size == 1 -> DealerFirstTurn(hand)
            score.isSame(BLACK_JACK_NUMBER) -> BlackJack(hand)
            score.isBiggerThan(DEALER_STAND_CONDITION) -> Stay(hand)
            else -> DealerHit(hand)
        }
    }
}
