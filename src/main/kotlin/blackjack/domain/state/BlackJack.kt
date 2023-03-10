package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class BlackJack(cards: Cards) : EndTurn(cards) {
    constructor(cards: List<Card>) : this(Cards(cards.toSet()))
}
