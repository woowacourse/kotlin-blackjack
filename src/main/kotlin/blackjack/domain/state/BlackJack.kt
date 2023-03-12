package blackjack.domain.state

import blackjack.domain.CardBunchForState

class BlackJack(override val hand: CardBunchForState) : Stopped
