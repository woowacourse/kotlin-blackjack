package blackjack.model

import blackjack.state.State.Finished.Stay
import blackjack.state.State.Running.Hit

class Player(val nickname: Nickname) : CardHolder() {
    fun drawCardsForPlayer(
        hitOrStay: (nickname: Nickname) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        while (state is Hit) {
            if (hitOrStay(nickname)) {
                takeCard(card = GameDeck.drawCard())
                showPlayerCards(this)
            } else {
                changeState(state = Stay)
            }
        }
    }
}
