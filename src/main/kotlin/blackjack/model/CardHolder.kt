package blackjack.model

import blackjack.state.Blackjack
import blackjack.state.BlackjackState
import blackjack.state.Bust
import blackjack.state.Normal

abstract class CardHolder {
    private var _hand: Hand = Hand()
    val hand: Hand
        get() = _hand

    fun addCard(card: Card) {
        _hand.plus(card)
    }

    fun drawCard(shouldDrawCard: () -> Boolean) {
        while (!isGameFinished() && shouldDrawCard()) {
            addCard(card = GameDeck.drawCard())
        }
    }

    fun getState(): BlackjackState {
        return when {
            hand.calculate() == THRESHOLD_BLACKJACK && hand.cards.size == BLACKJACK_CARD_SIZE -> Blackjack()
            hand.calculate() > THRESHOLD_BUST -> Bust()
            else -> Normal()
        }
    }

    private fun isGameFinished() = getState().isFinished

    fun calculateWinningStateAgainst(opponent: CardHolder) = getState().calculateGameResult(this, opponent)

    companion object {
        private const val BLACKJACK_CARD_SIZE = 2
        private const val THRESHOLD_BLACKJACK = 21
        const val THRESHOLD_BUST = 21
    }
}
