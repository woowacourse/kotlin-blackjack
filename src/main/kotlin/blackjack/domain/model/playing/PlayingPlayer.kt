package blackjack.domain.model.playing

import blackjack.domain.model.Card
import blackjack.domain.model.hand.Hands
import blackjack.domain.model.hand.state.Initial
import blackjack.domain.model.hand.state.State
import blackjack.domain.model.hand.strategy.PlayerStay

class PlayingPlayer(override var handsState: State, override val name: String) : PlayingParticipant() {
    constructor(name: String, vararg card: Card) : this(Initial(PlayerStay(), Hands(card.toList())), name)

    override fun showStartCards(): List<Card> = showCards().take(SHOW_START_PLAYER_CARD_COUNT)

    private companion object {
        const val SHOW_START_PLAYER_CARD_COUNT = 2
    }
}
