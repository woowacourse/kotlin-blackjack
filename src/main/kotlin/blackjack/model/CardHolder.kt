package blackjack.model

import blackjack.state.BlackjackState
import blackjack.state.Hit

sealed class CardHolder(val userInfo: UserInfo) {
    var blackjackState: BlackjackState = Hit()
        private set

    fun addCard(card: Card) {
        blackjackState = blackjackState.draw(card)
    }

    fun drawCard(
        gameDeck: GameDeck,
        shouldDrawCard: () -> Boolean,
        showPlayerCards: (cardHolder: CardHolder) -> Unit,
    ) {
        while (!blackjackState.isFinished()) {
            if (shouldDrawCard()) {
                addCard(card = gameDeck.drawCard())
            } else {
                blackjackState = blackjackState.stay()
            }
            showPlayerCards(this)
        }
    }

    fun getSumOfCards(): Int = blackjackState.calculateHand()

    fun calculateProfit(opponent: BlackjackState): Double {
        val gameResult = blackjackState.calculate(opponent)
        return blackjackState.profit(betAmount = userInfo.betAmount, gameResult = gameResult)
    }
}