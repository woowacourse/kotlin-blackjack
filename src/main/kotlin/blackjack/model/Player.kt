package blackjack.model

import blackjack.base.BaseHolder

class Player(override val humanName: HumanName) : BaseHolder() {
    fun drawCardsForPlayer(
        card: Card,
        hitOrStay: (humanName: HumanName) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        while (hand.state == UserState.RUNNING) {
            if (hitOrStay(humanName)) {
                takeCard(card = card)
                showPlayerCards(this)
            } else {
                hand.changeState(userState = UserState.STAY)
            }
        }
    }
}
