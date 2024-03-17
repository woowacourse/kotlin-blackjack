package blackjack.model.state

import blackjack.base.BaseHolder
import blackjack.model.Card
import blackjack.model.GameResult
import blackjack.model.Hand

sealed interface State {
    val hand: Hand

    fun decideWinner(opponent: BaseHolder): GameResult

    fun hitOrStay(isHit: Boolean): State

    fun getCard(card: Card): State

    fun updateState(totalPoint: Int): State

    sealed class Progressing : State
    sealed class Finished : State
}
