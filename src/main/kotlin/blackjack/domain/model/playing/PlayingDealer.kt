package blackjack.domain.model.playing

import blackjack.domain.model.Card
import blackjack.domain.model.hand.Hands
import blackjack.domain.model.hand.state.Hit
import blackjack.domain.model.hand.state.State
import blackjack.domain.model.hand.strategy.DealerStay

class PlayingDealer(override var handsState: State, override val name: String = DEALER_NAME) : PlayingParticipant() {
    constructor(vararg card: Card) : this(Hit(DealerStay(), Hands(card.toList())))

    override fun showStartCards() = showCards().take(SHOW_START_DEALER_CARD_COUNT)

    private companion object {
        const val DEALER_NAME = "딜러"
        const val SHOW_START_DEALER_CARD_COUNT = 1
    }
}
