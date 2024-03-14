package blackjack.model

import blackjack.state.BlackjackState
import blackjack.state.Finished
import blackjack.state.Hit

sealed class CardHolder(val userInfo: UserInfo) {
    private var blackjackState: BlackjackState = Hit()

    fun getState(): BlackjackState {
        return blackjackState
    }

    fun addCard(card: Card) {
        blackjackState = blackjackState.draw(card)
    }

    fun drawCard(
        gameDeck: GameDeck,
        shouldDrawCard: () -> Boolean,
        newCardHolder: (cardHolder: CardHolder) -> Unit,
    ) {
        while (!blackjackState.isFinished()) {
            if (shouldDrawCard()) {
                addCard(card = gameDeck.drawCard())
            } else {
                blackjackState = blackjackState.stay()
            }
            newCardHolder(this)
        }
    }

    fun getSumOfCards(): Int = blackjackState.hand().calculate()

    fun calculateProfit(opponent: Dealer): Double {
        val gameResult = (blackjackState as Finished).calculate(this, opponent)
        return (blackjackState as Finished).profit(betAmount = userInfo.betAmount, gameResult = gameResult)
    }

    companion object {
        const val THRESHOLD_BLACKJACK = 21
    }
}
