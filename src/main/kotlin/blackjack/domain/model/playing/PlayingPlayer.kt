package blackjack.domain.model.playing

import blackjack.domain.model.Card
import blackjack.domain.model.hand.Hands
import blackjack.domain.model.hand.PlayerHit
import blackjack.domain.model.hand.State

class PlayingPlayer(override var handsState: State, override val name: String) : PlayingParticipant() {
    constructor(name: String, vararg card: Card) : this(PlayerHit(Hands(card.toList())), name)

    override fun showStartCards(): List<Card> = showCards().take(2)
}
