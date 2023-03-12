package blackjack.domain.state

import blackjack.domain.CardBunch

class BlackJack(override val hand: CardBunch) : Stopped
