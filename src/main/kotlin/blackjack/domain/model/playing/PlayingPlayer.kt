package blackjack.domain.model.playing

import blackjack.domain.model.Card
import blackjack.domain.model.hand.Hands
import blackjack.domain.model.hand.Hit
import blackjack.domain.model.hand.PlayerStay
import blackjack.domain.model.hand.State

class PlayingPlayer(override var handsState: State, override val name: String) : PlayingParticipant() {
    constructor(name: String, vararg card: Card) : this(Hit(PlayerStay(), Hands(card.toList())), name)

    override fun showStartCards(): List<Card> = showCards().take(SHOW_START_PLAYER_CARD_COUNT)

    private companion object {
        const val SHOW_START_PLAYER_CARD_COUNT = 2
    }
}
