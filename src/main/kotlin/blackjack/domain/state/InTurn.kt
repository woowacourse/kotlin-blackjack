package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class InTurn(protected val cards: Cards) : State {
    override fun draw(card: Card): State {
        return FirstTurn(card)
    }
}
