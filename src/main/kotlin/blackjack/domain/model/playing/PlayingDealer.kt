package blackjack.domain.model.playing

import blackjack.domain.model.Card
import blackjack.domain.model.hand.DealerHit
import blackjack.domain.model.hand.Hands
import blackjack.domain.model.hand.State

class PlayingDealer(override var handsState: State, override val name: String = DEALER_NAME) : PlayingParticipant() {
    constructor(vararg card: Card) : this(DealerHit(Hands(card.toList())))

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
