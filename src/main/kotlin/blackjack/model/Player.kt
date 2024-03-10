package blackjack.model

import blackjack.state.State.Finished.Stay
import blackjack.state.State.Running.Hit

class Player(override val humanName: HumanName) : BaseHolder() {
    fun drawCardsForPlayer(
        gameDeck: GameDeck,
        hitOrStay: (humanName: HumanName) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        while (state is Hit) {
            if (hitOrStay(nickname)) {
                takeCard(card = gameDeck.drawCard())
                showPlayerCards(this)
            } else {
                changeState(state = Stay)
            }
        }
    }
}
