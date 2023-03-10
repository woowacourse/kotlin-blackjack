package blackjack.domain.state

import blackjack.domain.card.Card

interface CardState {
    fun draw(card: Card): CardState

    fun stay(): CardState
}
