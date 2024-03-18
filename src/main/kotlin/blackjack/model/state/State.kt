package blackjack.model.state

import blackjack.base.BaseHolder
import blackjack.model.Card
import blackjack.model.GameResult
import blackjack.model.Hand
import blackjack.model.Profit

sealed interface State {
    val hand: Hand
    val profit: Profit

    fun getCard(card: Card): State

    fun hitOrStay(isHit: Boolean): State

    fun updateState(totalPoint: Int): State

    fun decideWinner(opponent: BaseHolder): GameResult

    fun calculateProfitByOpponent(opponentProfit: Double)

    sealed class Progressing : State
    sealed class Finished : State
}
