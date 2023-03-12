package blackjack.domain.state

import blackjack.domain.CardBunch

class Hit(override val hand: CardBunch) : State
