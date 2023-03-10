package blackjack.domain.state

import blackjack.domain.card.Cards

class Stay(cards: Cards) : EndTurn(cards)
