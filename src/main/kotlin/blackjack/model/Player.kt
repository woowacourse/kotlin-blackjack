package blackjack.model

import blackjack.base.BaseHolder

class Player(override val humanName: HumanName) : BaseHolder() {
    fun drawCardsForPlayer(
        gameDeck: GameDeck,
        hitOrStay: (humanName: HumanName) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        while (hand.state == UserState.RUNNING) {
            if (hitOrStay(humanName)) {
                takeCard(card = gameDeck.drawCard())
                showPlayerCards(this)
            } else {
                hand.changeState(userState = UserState.STAY)
            }
        }
    }
}
