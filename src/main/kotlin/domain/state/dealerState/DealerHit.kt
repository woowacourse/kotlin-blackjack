package domain.state.dealerState

import domain.Score
import domain.card.Card
import domain.card.Hand
import domain.constant.BlackJackConstants
import domain.state.Bust
import domain.state.Started
import domain.state.State
import domain.state.Stay

class DealerHit(hand: Hand) : Started(hand) {
    override fun draw(card: Card): State {
        val score = Score.of(hand.apply { add(card) })
        if (score.isBiggerThan(BlackJackConstants.BLACK_JACK_NUMBER)) return Bust(hand)
        return Stay(hand)
    }
}
