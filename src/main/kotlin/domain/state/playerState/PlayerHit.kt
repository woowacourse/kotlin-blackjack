package domain.state.playerState

import domain.Score
import domain.card.Card
import domain.card.Hand
import domain.constant.BlackJackConstants.BLACK_JACK_NUMBER
import domain.state.Bust
import domain.state.Started
import domain.state.State

class PlayerHit(hand: Hand) : Started(hand) {
    override fun draw(card: Card): State {
        val score = Score.of(hand.apply { add(card) })
        if (score.isBiggerThan(BLACK_JACK_NUMBER)) return Bust(hand)
        return PlayerHit(hand)
    }
}
