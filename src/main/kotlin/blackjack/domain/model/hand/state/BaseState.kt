package blackjack.domain.model.hand.state

import blackjack.domain.model.Card
import blackjack.domain.model.Score
import blackjack.domain.model.hand.Hands

abstract class BaseState(protected var hands: Hands) : State {
    override fun cards(): List<Card> = hands.cards.map { it.copy() }

    override fun score(): Score = hands.score()
}
