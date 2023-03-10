package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.participants.Dealer
import blackjack.domain.result.Outcome

class BlackJack(cards: Cards = Cards()) : EndTurn(cards) {
    constructor(cards: List<Card>) : this(Cards(cards.toSet()))
    constructor(vararg cards: Card) : this(Cards(cards.toSet()))

    override fun matchWith(dealer: Dealer): Outcome =
        Outcome.WIN_WITH_BLACKJACK
}
