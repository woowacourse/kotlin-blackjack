package blackjack.model

import blackjack.base.BaseHolder

class Player(override val humanName: HumanName) : BaseHolder() {
    fun chooseHitOrStay(
        gameDeck: GameDeck,
        hitOrStay: (humanName: HumanName) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        while (hand.state == UserState.RUNNING) {
            if (hitOrStay(humanName)) {
                takeCard(gameDeck.drawCard())
                showPlayerCards(this)
            } else {
                hand.changeState(UserState.STAY)
            }
        }
    }
}
