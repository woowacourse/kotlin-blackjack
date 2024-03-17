package blackjack.model

import blackjack.state.Hit
import blackjack.state.State

sealed class CardHolder(val userInfo: UserInfo) {
    var state: State = Hit()
        private set

    fun addCard(card: Card) {
        state = state.draw(card)
    }

    fun drawCard(
        card: () -> Card,
        shouldDrawCard: () -> Boolean,
        showPlayerCards: (cardHolder: CardHolder) -> Unit,
    ) {
        while (!state.isFinished()) {
            if (shouldDrawCard()) {
                addCard(card = card())
            } else {
                state = state.stay()
            }
            showPlayerCards(this)
        }
    }

    fun getSumOfCards(): Int = state.calculateHand()

    fun calculateProfit(opponent: State): Double {
        val gameResult = state.calculate(opponent)
        return state.profit(betAmount = userInfo.betAmount, gameResult = gameResult)
    }
}
