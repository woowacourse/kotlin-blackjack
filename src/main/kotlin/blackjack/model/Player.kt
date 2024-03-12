package blackjack.model

import blackjack.state.BlackjackState.Finished.Stay
import blackjack.state.BlackjackState.Running.Hit

class Player(val nickname: Nickname) : CardHolder() {
    fun drawCardsForPlayer(
        hitOrStay: (nickname: Nickname) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        while (blackjackState is Hit) {
            drawDecision(hitOrStay = hitOrStay, showPlayerCards = showPlayerCards)
        }
    }

    private fun drawDecision(
        hitOrStay: (nickname: Nickname) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        if (hitOrStay(nickname)) {
            addCard(card = GameDeck.drawCard())
            showPlayerCards(this)
        } else {
            changeState(blackjackState = Stay)
        }
    }
}
