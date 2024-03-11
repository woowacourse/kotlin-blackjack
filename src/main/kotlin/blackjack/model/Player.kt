package blackjack.model

import blackjack.base.BaseHolder

class Player(val name: Name) : BaseHolder() {
    fun chooseHitOrStay(
        gameDeck: GameDeck,
        hitOrStay: (name: Name) -> Boolean,
        returnPlayerInfo: (player: Player) -> Unit,
    ) {
        while (hand.state == UserState.RUNNING) {
            if (hitOrStay(name)) {
                takeCard(gameDeck.drawCard())
            } else {
                hand.changeState(UserState.STAY)
            }
            returnPlayerInfo(this)
        }
    }
}
