package blackjack.domain.state

import blackjack.domain.card.Cards

class Bust(cards: Cards) : EndTurn(cards)
