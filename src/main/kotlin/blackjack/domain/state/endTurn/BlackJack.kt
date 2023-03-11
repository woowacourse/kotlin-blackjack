package blackjack.domain.state.endTurn

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.Outcome
import blackjack.domain.state.State

class BlackJack(cards: Cards = Cards()) : EndTurn(cards) {
    constructor(cards: List<Card>) : this(Cards(cards.toSet()))
    constructor(vararg cards: Card) : this(Cards(cards.toSet()))

    override fun matchWith(otherState: State): Outcome = Outcome.WIN_WITH_BLACKJACK
}
