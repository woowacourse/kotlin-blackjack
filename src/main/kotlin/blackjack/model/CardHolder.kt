package blackjack.model

import blackjack.state.Blackjack
import blackjack.state.BlackjackState
import blackjack.state.Finished
import blackjack.state.Hit

sealed class CardHolder(val userInfo: UserInfo) {
    private var blackjackState: BlackjackState = Hit()

    fun getState(): BlackjackState {
        val hand = blackjackState.hand()
        return when (hand.calculate()) {
            THRESHOLD_BLACKJACK -> Blackjack(hand)
            else -> Hit(hand)
        }
    }

    fun addCard(card: Card) {
        blackjackState = blackjackState.draw(card)
    }

    fun drawCard(
        shouldDrawCard: () -> Boolean,
        newCardHolder: (cardHolder: CardHolder) -> Unit,
    ) {
        while (!blackjackState.isFinished()) {
            if (shouldDrawCard()) {
                addCard(card = GameDeck.drawCard())
            } else {
                blackjackState = blackjackState.stay()
            }
            newCardHolder(this)
        }
    }

    fun getSumOfCards(): Int = blackjackState.hand().calculate()

    fun calculateWinningStateAgainst(opponent: CardHolder) = (blackjackState as Finished).calculate(opponent)

    companion object {
        const val THRESHOLD_BLACKJACK = 21
    }
}
