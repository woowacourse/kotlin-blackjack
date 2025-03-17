package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card

interface PlayingState {
    val hand: Hand

    fun draw(card: Card): PlayingState
}
