package blackjack.domain.state.endTurn

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.Outcome
import blackjack.domain.state.Outcome.WIN_WITH_BLACKJACK
import blackjack.domain.state.State

class BlackJack(cards: Cards = Cards()) : EndTurn(cards) {
    constructor(cards: Set<Card>) : this(Cards(cards))

    override fun matchWith(otherState: State): Outcome = WIN_WITH_BLACKJACK
}
