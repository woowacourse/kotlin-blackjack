package blackjack.domain.model.hand

import blackjack.domain.model.Card

abstract class Initial(protected var hands: Hands) : State {
    override fun cards(): List<Card> = hands.cards.map { it.copy() }

    override fun score() = hands.getScore()
}
