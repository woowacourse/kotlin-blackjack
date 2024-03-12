package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand

interface CardsState {
    fun draw(card: Card): CardsState

    fun stay(): CardsState

    fun getCardHands(): CardHand

    fun countCards(): Int
}
