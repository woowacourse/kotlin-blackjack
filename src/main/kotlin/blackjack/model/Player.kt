package blackjack.model

import blackjack.base.BaseHolder

class Player(val name: Name) : BaseHolder() {
    fun chooseHitOrStay(
        gameDeck: GameDeck,
        hitOrStay: (name: Name) -> Boolean,
        returnPlayerInfo: (player: Player) -> Unit,
    ) {
        while (state is Running) {
            changeState(state.hitOrStay(hitOrStay(name)))
            takeCard(gameDeck.drawCard())
            returnPlayerInfo(this)
        }
    }
}
