package domain.state.playerState

import domain.Score
import domain.card.Card
import domain.card.Hand
import domain.constant.BlackJackConstants.BLACK_JACK_NUMBER
import domain.state.BlackJack
import domain.state.Started
import domain.state.State

class PlayerFirstTurn(hand: Hand) : Started(hand) {
    override fun draw(card: Card): State {
        val score = Score.of(hand.apply { add(card) })
        return when {
            hand.value.size == 1 -> PlayerFirstTurn(hand)
            score.isSame(BLACK_JACK_NUMBER) -> BlackJack(hand)
            else -> PlayerHit(hand)
        }
    }
}
