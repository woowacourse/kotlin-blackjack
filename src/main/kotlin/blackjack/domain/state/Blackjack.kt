package blackjack.domain.state

import blackjack.domain.card.Cards

class Blackjack(private val cards: Cards = Cards()) : State
