package blackjack.domain.state

import blackjack.domain.card.Card

interface PlayingState {
    fun draw(card: Card): PlayingState
}
